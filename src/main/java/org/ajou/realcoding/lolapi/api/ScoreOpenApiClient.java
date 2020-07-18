package org.ajou.realcoding.lolapi.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
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

    private static final String QueueTypeREQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-39a256b7-c406-4a68-9b07-e4185b4ec41a";

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId){
        SoloRankInfo[] response = restTemplate.getForObject(QueueTypeREQUEST_URI, SoloRankInfo[].class, encryptedSummonerId);
        List<SoloRankInfo> soloRankInfoList = Arrays.asList(response);
        for (SoloRankInfo soloRankInfo : soloRankInfoList) {
            log.info(soloRankInfo.toString());
        }

        return soloRankInfoList;
    }
}
