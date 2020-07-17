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

    public void getMatchData(long gameId){
        MatchData currentMatchData = scoreOpenApiClient.getMatchData(gameId);

        MatchData currentMatchFromDb = currentScoreRepository.findCurrentMatchDataByGameId(gameId);

        if (currentMatchFromDb == null || currentMatchFromDb.getGameId() != currentMatchData.getGameId()) {
            MatchData insertedOrUpdatedMatchData = currentScoreRepository.insertOrUpdatedCurrentMatchData(currentMatchData);
            log.info("CurrentMatchData has inserted or updated successfully. CurrentWeather : {}", insertedOrUpdatedMatchData);
        }
    }
    public MatchData getCurrentMatchDataByGameId(long gameId) {
        return currentScoreRepository.findCurrentMatchDataByGameId(gameId);
    }

}
