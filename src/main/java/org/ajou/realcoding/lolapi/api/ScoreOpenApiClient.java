package org.ajou.realcoding.lolapi.api;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.GameIds;
import org.ajou.realcoding.lolapi.domain.MatchData;
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

    private final String apiKey = "RGAPI-4874d657-5f9f-44ec-94da-c40d8f812b9f";

    private static final String USERINFO_REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apikey}";
    private static final String QUEUETYPE_REQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={apiKey}";
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key={apiKey}";
    private static final String MATCHDATA_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}?api_key={apiKey}";

    public UserInfo getUserInfo(String summonerName){
        log.info("Requesting to API ...");
        return restTemplate.getForObject(USERINFO_REQUEST_URI, UserInfo.class, summonerName, apiKey);
    }

    //json형태의 파일을 객체로 반환받는다.
    public List<SoloRankInfo> getSoloRankInfo(String summonerId){
        SoloRankInfo[] response = restTemplate.getForObject(QUEUETYPE_REQUEST_URI, SoloRankInfo[].class, summonerId, apiKey);
        List<SoloRankInfo> soloRankInfoList = Arrays.asList(response);
        /*for (SoloRankInfo soloRankInfo : soloRankInfoList) {
            log.info(soloRankInfo.toString());
        }*/

        return soloRankInfoList;
    }

    public MatchData getMatchData(String gameId) {
        return restTemplate.getForObject(MATCHDATA_REQUEST_URI, MatchData.class, gameId, apiKey);
    }

    //API를 호출하여, gameIds를 return하는 메소드.
    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = restTemplate.getForObject(GAMEID_REQUEST_URI, GameIds.class, accountId, apiKey);
        return gameIds;
    }
}
