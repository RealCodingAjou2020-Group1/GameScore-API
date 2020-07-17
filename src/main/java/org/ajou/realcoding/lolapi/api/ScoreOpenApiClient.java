package org.ajou.realcoding.lolapi.api;

import org.ajou.realcoding.lolapi.domain.MatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "RGAPI-c364d187-ecf5-4e7b-8dd3-bf48206d3032";
    private final String matchDataUri = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}}?api_key={apiKey}";
    public MatchData getMatchData(long gameId) {
        MatchData matchData = restTemplate.exchange(matchDataUri, HttpMethod.GET, null, MatchData.class, gameId, apiKey)
                .getBody();
        return matchData;
    }
}
