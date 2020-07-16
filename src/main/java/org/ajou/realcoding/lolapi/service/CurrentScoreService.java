package org.ajou.realcoding.lolapi.service;

import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public UserInfo getUserInfo(String summonerName) {
        return currentScoreRepository.findUserInfoByName(summonerName);
    }

    public void saveUserInfo(String summonerName) {
        UserInfo userInfo = scoreOpenApiClient.getUserInfo(summonerName);
        currentScoreRepository.saveCurrentSummonerName(userInfo);
    }


}
