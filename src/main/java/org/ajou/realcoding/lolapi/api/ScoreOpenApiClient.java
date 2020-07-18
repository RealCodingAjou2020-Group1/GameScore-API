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

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-e767475c-1faf-4aca-8d30-4d17e9cf6c4a";

    public UserInfo getUserInfo(String summonerName){
        log.info("Requesting to API ...");
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName);
    }
}
