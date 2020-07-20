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

    private final String apiKey = "RGAPI-aa5efa04-45bd-4cd5-bcb8-7026464d0c01";

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key={apiKey}";
    private static final String QUEUE_TYPE_REQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={apiKey}";
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key={apiKey}";
    private static final String MATCHDATA_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}?api_key={apiKey}";

    public UserInfo getUserInfo(String summonerName){
        log.info("Requesting to API ...");
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName, apiKey);
    }

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId){
        SoloRankInfo[] response = restTemplate.getForObject(QUEUE_TYPE_REQUEST_URI, SoloRankInfo[].class, encryptedSummonerId, apiKey);
        List<SoloRankInfo> soloRankInfoList = Arrays.asList(response);
        for (SoloRankInfo soloRankInfo : soloRankInfoList) {
            log.info(soloRankInfo.toString());
        }

        return soloRankInfoList;
    }

    public MatchData getMatchData(String gameId) {
        return restTemplate.getForObject(MATCHDATA_REQUEST_URI, MatchData.class, gameId, apiKey);
    }

    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = restTemplate.getForObject(GAMEID_REQUEST_URI, GameIds.class, accountId, apiKey);
        return gameIds;
    }
}
