package org.ajou.realcoding.lolapi.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-655b0efa-a0b2-4f8c-93c9-9c69a9198bb1";

    public UserInfo getUserInfo(String summonerName){
        log.info("API request completed!");
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName);
    }
}
