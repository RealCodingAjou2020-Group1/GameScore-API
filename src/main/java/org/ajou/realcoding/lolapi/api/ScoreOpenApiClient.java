package org.ajou.realcoding.lolapi.api;

import org.ajou.realcoding.lolapi.domain.GameIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreOpenApiClient
{
    @Autowired
    private RestTemplate restTemplate;
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key=RGAPI-34a47775-121a-47d9-b467-974d8bb34623";

    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = restTemplate.getForObject(GAMEID_REQUEST_URI, GameIds.class, accountId);
        return gameIds;
    }
}
