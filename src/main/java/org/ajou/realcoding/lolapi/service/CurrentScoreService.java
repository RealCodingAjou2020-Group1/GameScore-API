package org.ajou.realcoding.lolapi.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.*;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CurrentScoreService {

    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    //summonerName 으로 DB 에서 해당 userInfo 를 찾고, 없으면 API 에서 호출 후 DB 저장 하고 return
    public UserInfo getUserInfo(String summonerName) {
        UserInfo currentUserInfoFromDb = currentScoreRepository.findUserInfoBySummonerName(summonerName);

        if(currentUserInfoFromDb == null) {
            UserInfo currentUserInfo = scoreOpenApiClient.getUserInfo(summonerName);
            UserInfo insertedOrUpdatedCurrentUserInfo = currentScoreRepository.insertOrUpdatedCurrentUserInfo(currentUserInfo);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", insertedOrUpdatedCurrentUserInfo);
            return insertedOrUpdatedCurrentUserInfo;
        }
        log.info("Already exists. CurrentUserInfo : {}", currentUserInfoFromDb);
        return currentUserInfoFromDb;
    }

    //암호화된 summonerId로 DB에서 해당 solorankinfo를 찾고, 없다면 API에서 호출한 뒤 DB에 저장하고 return
    public List<SoloRankInfo> getSoloRankInfo(String summonerId) {

        List<SoloRankInfo> currentSoloRankInfoFromDb = currentScoreRepository.findSoloRankInfoByEncryptedSummonerId(summonerId);

        if(currentSoloRankInfoFromDb.isEmpty()){
            List<SoloRankInfo> soloRankInfo = scoreOpenApiClient.getSoloRankInfo(summonerId);
            currentScoreRepository.insertOrUpdateCurrentSoloRankInfo(soloRankInfo);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", currentSoloRankInfoFromDb);
            return currentScoreRepository.findSoloRankInfoByEncryptedSummonerId(summonerId);
        }
        log.info("Already exists. CurrentUserInfo : {}", currentSoloRankInfoFromDb);
        return currentSoloRankInfoFromDb;
    }


    //accountID에 해당하는 100경기의 gameid들을 api를 호출하거나 DB에 있는 데이터 확인하여 return
    //처음 검색하는 acountId거나, 검색한 지 20분이 지났다면, API를 호출하여 그 값을 return.
    //검색한 지 20분이 지나지 않았다면, DB에 있는 데이터를 return.
    public GameIds getGameId(String accountId) throws ParseException {
        Date curDate = new Date();
        long saveTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        curDate = dateFormat.parse(dateFormat.format(curDate));
        long curDateTime = curDate.getTime(); //현재 시간
        log.info("Current time :{}", curDate);

        GameIds gameIdsFromDb = currentScoreRepository.findGameIdsByAccountId(accountId);
        if(gameIdsFromDb != null)
             saveTime = gameIdsFromDb.getTime().getTime();//accountId에 해당하는 gameid가 저장된 시간
        else saveTime = 0;
        //log.info("Saved time :{}", saveTime);
        long diff = (curDateTime - saveTime) / 60000;

        if(diff > 20 || gameIdsFromDb == null){
            //accountId에 해당하는 데이터가 DB에 없거나 저장된 시간과 현재 시간의 차이가 20분이 넘어가면 새로 저장한다.
            GameIds gameIds = scoreOpenApiClient.getGameId(accountId);
            gameIds.setTime(curDate);
            gameIds.setAccountId(accountId);
            log.info("Save Time :{}", curDate);
            currentScoreRepository.saveGameId(gameIds);
            return gameIds;
        }
        else{//만약 이미 저장되어 있거나 시간이 별로 차이 안난다면 DB에 있는 데이터를 가져온다.
            log.info("Saved time :{}", curDate);
            GameIds findGameIds = currentScoreRepository.findGameIdsByAccountId(accountId);
            return findGameIds;
        }
    }

    //전달받은 GameIds중 최근 5개의 gameid를 뽑아내어 list로 만들어 return
    public List<String> getFiveGameId(GameIds gameIds) {
        List<String> fiveGameIds = new ArrayList<>();
        List<GameIds.MatchReferenceDto> allGameIds = gameIds.getMatches();

        for(int i = 0; i < 5; i++){ //입력받은 GameIds 중 5개만 가져온다.
            fiveGameIds.add(String.valueOf(allGameIds.get(i).getGameId()));
        }
        return fiveGameIds;
    }

    //최근 5개의 gameid를 분석하여 result 객체에 넣어 return
    public Result getAnalysis(List<String> gameIds, String accountId, String summonerName){
        Result currenResultFromDb = currentScoreRepository.findCurrentResultByAccountId(accountId);
        List<Analysis> resultAnalysis = new ArrayList<Analysis>();

        if(currenResultFromDb == null || currenResultFromDb.getAnalysisList().get(0).getGameId() != Long.parseLong(gameIds.get(0))){
            //해당 accountId의 분석결과가 없거나 새로운 game에 참여했으면 업데이트 한다.
            for(String matchId : gameIds){
                long gameId = Long.parseLong(matchId);
                Analysis currentAnalysis = analyzeMatchData(scoreOpenApiClient.getMatchData(matchId), summonerName);
                resultAnalysis.add(currentAnalysis);
            }
            Result currentResult = new Result();
            currentResult.setAccountId(accountId);
            currentResult.setAnalysisList(resultAnalysis);
            currentScoreRepository.insertOrUpdatedCurrentResult(currentResult);
            log.info("New Result added: {}", currentResult);
            return currentResult;
        }
        //아니라면 DB에 있는 데이터를 가져온다.
        log.info("Result already exists : {}", currenResultFromDb);
        return currenResultFromDb;
    }

    //api를 통해 받은 matchdata를 분석하여 현재 summonerName에 대한 정보를 찾아내어 return
    public Analysis analyzeMatchData(MatchData matchData, String summonerName){
        Analysis currentAnalysis = new Analysis();
        //입력받은 matchData를 분석하여 필요한 정보를 뽑아낸다.
        List<MatchData.ParticipantIdentityDto> participantIdentities = matchData.getParticipantIdentities();
        List<MatchData.ParticipantDto> participant = matchData.getParticipants();
        List<MatchData.TeamStatsDto> teamStats = matchData.getTeams();

        currentAnalysis.setGameId(matchData.getGameId());

        for(int i = 0; i < participantIdentities.size(); i++){
            //해당 소환사명의 participantID를 찾는다.
            if(summonerName.equalsIgnoreCase(participantIdentities.get(i).getPlayer().getSummonerName())){
                currentAnalysis.setParticipantId(participantIdentities.get(i).getParticipantId());
                for(int j = 0; j < participant.size(); j++){
                    if(currentAnalysis.getParticipantId() == participant.get(j).getParticipantId()){
                        //participantID를 이용하여 해당 소환사의 인게임 정보를 가져온다.
                        currentAnalysis.setTeamId(participant.get(j).getTeamId());
                        currentAnalysis.setChampionId(participant.get(j).getChampionId());
                        currentAnalysis.setKill(participant.get(j).getStats().getKills());
                        currentAnalysis.setDeath(participant.get(j).getStats().getDeaths());
                        currentAnalysis.setAssists(participant.get(j).getStats().getAssists());

                        for(int k = 0; k <teamStats.size(); k++){
                            if(currentAnalysis.getTeamId() == teamStats.get(k).getTeamId()){
                                //TeamId를 이용하여 승패여부를 확인한다.
                                currentAnalysis.setWin(teamStats.get(k).getWin());
                            }
                        }
                    }
                }
            }
        }
        return currentAnalysis;
    }
}
