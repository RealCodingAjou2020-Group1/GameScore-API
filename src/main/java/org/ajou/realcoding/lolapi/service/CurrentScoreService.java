package org.ajou.realcoding.lolapi.service;


import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.*;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public MatchData getMatchData(String matchId){
        MatchData currentMatchData = scoreOpenApiClient.getMatchData(matchId);

        long gameId = Long.parseLong(matchId);

        MatchData currentMatchFromDb = currentScoreRepository.findCurrentMatchDataByGameId(gameId);

        if (currentMatchFromDb == null || currentMatchFromDb.getGameId() != currentMatchData.getGameId()) {
            MatchData insertedOrUpdatedMatchData = currentScoreRepository.insertOrUpdatedCurrentMatchData(currentMatchData);
            log.info("New MatchData has inserted or updated successfully. CurrentMatchData : {}", insertedOrUpdatedMatchData);
            return insertedOrUpdatedMatchData;
        }
        log.info("Already exists. CurrentMatchData : {}", currentMatchFromDb);
        return currentMatchFromDb;
    }

    public Analysis analyzeMatchData(MatchData matchData, String summonerName){
        Analysis analysis = new Analysis();

        List<ParticipantIdentityDto> participantIdentities = matchData.getParticipantIdentities();
        List<ParticipantDto> participant = matchData.getParticipants();
        List<TeamStatsDto> teamStats = matchData.getTeams();

        for(int i = 0; i < participantIdentities.size(); i++){
            if(summonerName.equals(participantIdentities.get(i).getPlayer().getSummonerId())){
                analysis.setParticipantId(participantIdentities.get(i).getParticipantId());

                for(int j = 0; j < participant.size(); j++){
                    if(analysis.getParticipantId() == participant.get(j).getParticipantId()){
                        analysis.setTeamId(participant.get(j).getTeamId());
                        analysis.setChampionId(participant.get(j).getChampionId());
                        analysis.setKill(participant.get(j).getStats().getKills());
                        analysis.setDeath(participant.get(j).getStats().getDeaths());
                        analysis.setAssists(participant.get(j).getStats().getAssists());

                        for(int k = 0; k <teamStats.size(); k++){
                            if(analysis.getTeamId() == teamStats.get(k).getTeamId()){
                                analysis.setWin(teamStats.get(k).getWin());
                            }
                        }
                    }
                }
            }
        }

        return analysis;
    }
}
