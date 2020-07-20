package org.ajou.realcoding.lolapi.service;

public class CurrentScoreService {

    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public UserInfo getUserInfo(String summonerName) {

        UserInfo currentUserInfoFromDb = currentScoreRepository.findUserInfoByName(summonerName);

        if(currentUserInfoFromDb == null) {
            UserInfo currentUserInfo = scoreOpenApiClient.getUserInfo(summonerName);
            UserInfo insertedOrUpdatedCurrentUserInfo = currentScoreRepository.insertOrUpdatedCurrentUserInfo(currentUserInfo);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", insertedOrUpdatedCurrentUserInfo);
            return insertedOrUpdatedCurrentUserInfo;
        }
        log.info("Already exists. CurrentUserInfo : {}", currentUserInfoFromDb);
        return currentUserInfoFromDb;
    }



    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId) {

        List<SoloRankInfo> currentSoloRankInfoFromDb = currentScoreRepository.findSoloRankInfo(encryptedSummonerId);

        if(currentSoloRankInfoFromDb.isEmpty()){
            List<SoloRankInfo> soloRankInfo = scoreOpenApiClient.getSoloRankInfo(encryptedSummonerId);
            currentScoreRepository.insertOrUpdateCurrentSoloRankInfo(soloRankInfo);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", currentSoloRankInfoFromDb);
            return currentScoreRepository.findSoloRankInfo(encryptedSummonerId);
        }
        log.info("Already exists. CurrentUserInfo : {}", currentSoloRankInfoFromDb);
        return currentSoloRankInfoFromDb;
    }



    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public GameIds getGameId(String accountId) {
        GameIds gameIds = scoreOpenApiClient.getGameId(accountId);
        GameIds currentGameId = currentScoreRepository.findGameIds(accountId);

        if (currentGameId == null || gameIds.getTotalGames() != currentGameId.getTotalGames()) {
            gameIds.setAccountId(accountId);
            GameIds insertedOrUpdatedGameId = currentScoreRepository.saveGameId(gameIds);
            log.info("New GameId has inserted or updated successfully. InsertedOrUpdateGameId : {}", insertedOrUpdatedGameId);
            return insertedOrUpdatedGameId;
        }
        else{
            log.info("Already exists. CurrentGameId : {}", currentGameId);
            return currentGameId;
        }
    }

    public List<String> getFiveGameId(GameIds gameIds) {
        List<String> fiveGameIds = new ArrayList<>();
        List<GameIds.MatchReferenceDto> allGameIds = gameIds.getMatches();

        for(int i = 0; i < 5; i++){
            fiveGameIds.add(String.valueOf(allGameIds.get(i).getGameId()));
        }

        return fiveGameIds;
    }

    public List<Analysis> getAnalysis(List<String> gameIds, String summonerName){
        List<Analysis> resultAnalysis = new ArrayList<Analysis>();

        for(String matchId : gameIds){
            Analysis currentAnalysis = analyzeMatchData(scoreOpenApiClient.getMatchData(matchId), summonerName);
            long gameId = Long.parseLong(matchId);
            Analysis currentAnalysisFromDb = currentScoreRepository.findCurrentAnalysisByGameIdAndName(gameId, summonerName);
            if (currentAnalysisFromDb == null || currentAnalysisFromDb.getGameId() != currentAnalysis.getGameId()) {
                Analysis insertedOrUpdatedAnalysis = currentScoreRepository.insertOrUpdatedCurrentAnalysis(currentAnalysis);
                log.info("New Analysis has inserted or updated successfully. CurrentAnalysis : {}", insertedOrUpdatedAnalysis);
                resultAnalysis.add(insertedOrUpdatedAnalysis);
            }
            else{
                log.info("Already exists. CurrentAnalysis : {}", currentAnalysisFromDb);
                resultAnalysis.add(currentAnalysisFromDb);
            }
        }
        return resultAnalysis;
    }



    public Analysis analyzeMatchData(MatchData matchData, String summonerName){
        Analysis currentAnalysis = new Analysis();

        List<MatchData.ParticipantIdentityDto> participantIdentities = matchData.getParticipantIdentities();
        List<MatchData.ParticipantDto> participant = matchData.getParticipants();
        List<MatchData.TeamStatsDto> teamStats = matchData.getTeams();

        currentAnalysis.setSummonerName(summonerName);
        currentAnalysis.setGameId(matchData.getGameId());

        for(int i = 0; i < participantIdentities.size(); i++){
            if(summonerName.equals(participantIdentities.get(i).getPlayer().getSummonerName())){
                currentAnalysis.setParticipantId(participantIdentities.get(i).getParticipantId());
                log.info("ParticipantId : {}", currentAnalysis.getParticipantId());
                for(int j = 0; j < participant.size(); j++){
                    if(currentAnalysis.getParticipantId() == participant.get(j).getParticipantId()){
                        currentAnalysis.setTeamId(participant.get(j).getTeamId());
                        currentAnalysis.setChampionId(participant.get(j).getChampionId());
                        currentAnalysis.setKill(participant.get(j).getStats().getKills());
                        currentAnalysis.setDeath(participant.get(j).getStats().getDeaths());
                        currentAnalysis.setAssists(participant.get(j).getStats().getAssists());
                        log.info("Participant Stat : {}", participant.get(j).getStats());

                        for(int k = 0; k <teamStats.size(); k++){
                            if(currentAnalysis.getTeamId() == teamStats.get(k).getTeamId()){
                                currentAnalysis.setWin(teamStats.get(k).getWin());
                                log.info("Participant Win : {}", teamStats.get(k));
                            }
                        }
                    }
                }
            }
        }
        return currentAnalysis;
    }
}
