package org.ajou.realcoding.lolapi.api;

import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-62c56101-e63e-48d9-a4cb-d76b10d0ff87";

    public UserInfo getUserInfo(String summonerName){
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName);
    }
}
