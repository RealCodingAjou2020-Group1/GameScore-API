package org.ajou.realcoding.lolapi.api;

import org.ajou.realcoding.lolapi.domain.GameIds;
import org.ajou.realcoding.lolapi.domain.MatchData;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "RGAPI-98bfacd4-e4af-41f7-a54a-58905deaad50";
    private final String matchDataUri = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}?api_key={apiKey}";

    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key={apiKey}";

    public MatchData getMatchData(String gameId) {
        return restTemplate.getForObject(matchDataUri, MatchData.class, gameId, apiKey);
    }

    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = restTemplate.getForObject(GAMEID_REQUEST_URI, GameIds.class, accountId, apiKey);
        return gameIds;
    }
}
