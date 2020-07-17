package org.ajou.realcoding.lolapi.service;


import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.MatchData;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
