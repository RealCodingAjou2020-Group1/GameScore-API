package org.ajou.realcoding.lolapi.api;

import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.jws.soap.SOAPBinding;

@Service
public class ScoreOpenApiClient
{
    @Autowired
    private RestTemplate restTemplate;
    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key=RGAPI-b746212d-68ce-4165-aa9d-bcffe96c00a4";

    public UserInfo getGameId(String accountId)
    {
        UserInfo userInfo = restTemplate.getForObject(GAMEID_REQUEST_URI, UserInfo.class, accountId);
        return userInfo;
    }
}
