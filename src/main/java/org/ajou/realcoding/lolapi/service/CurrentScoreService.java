package org.ajou.realcoding.lolapi.service;

import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.GameIds;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class CurrentScoreService
{
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    @GetMapping("/lol/match/v4/matchlists/by-account")
    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = scoreOpenApiClient.getGameId(accountId);
        if(currentScoreRepository.findGameIds(accountId) == null){}
        else
        {
            currentScoreRepository.saveGameId(gameIds);
        }
        return gameIds;
    }


}
