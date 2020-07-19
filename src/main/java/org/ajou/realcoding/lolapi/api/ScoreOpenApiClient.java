package org.ajou.realcoding.lolapi.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-12220222-ebfc-4eaa-af12-f9f055506df9";
    private static final String QUEUE_TYPE_REQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-01e036eb-2f4a-443d-abc0-25d0c58850a7";

    public UserInfo getUserInfo(String summonerName){
        log.info("Requesting to API ...");
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName);
    }

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId){
        SoloRankInfo[] response = restTemplate.getForObject(QUEUE_TYPE_REQUEST_URI, SoloRankInfo[].class, encryptedSummonerId);
        List<SoloRankInfo> soloRankInfoList = Arrays.asList(response);
        for (SoloRankInfo soloRankInfo : soloRankInfoList) {
            log.info(soloRankInfo.toString());
        }

        return soloRankInfoList;
    }
}
