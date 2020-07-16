package org.ajou.realcoding.lolapi.api;


import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ScoreOpenApiClient {

    @Autowired
    private RestTemplate restTemplate;
    private static final String USERID_REQUEST_URI = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/hide%20on%20bush?api_key=RGAPI-0820cbe5-d609-4cff-a260-430ac60d95a0";


    public UserInfo getCurrentUserId() {
        UserInfo userInfo = restTemplate.getForObject(USERID_REQUEST_URI, UserInfo.class);
        return userInfo;
    }
}
