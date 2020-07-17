package org.ajou.realcoding.lolapi.api;

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

    private final String apiKey = "RGAPI-601029be-62ec-4861-877c-164b46aa5b7d";
    private final String matchDataUri = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}?api_key={apiKey}";
    public MatchData getMatchData(String gameId) {
        return restTemplate.getForObject(matchDataUri, MatchData.class, gameId, apiKey);
    }
}
