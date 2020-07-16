package org.ajou.realcoding.lolapi.service;

import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class CurrentScoreService {

    @Autowired
    private CurrentScoreRepository currentScoreRepository;
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;

    public void getUserIdOfHideOnBush() {
        UserInfo userInfo = scoreOpenApiClient.getCurrentUserId();
        currentScoreRepository.saveCurrentUserInfo(userInfo);
    }
}
