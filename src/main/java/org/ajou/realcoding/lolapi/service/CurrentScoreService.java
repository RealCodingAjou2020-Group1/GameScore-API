package org.ajou.realcoding.lolapi.service;

import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public SoloRankInfo getQueueType(String encryptedSummonerId) {
        SoloRankInfo soloRankInfo = scoreOpenApiClient.getQueueType(encryptedSummonerId);
        currentScoreRepository.saveCurrentSummonerName(soloRankInfo);
        return currentScoreRepository.findUserInfoByName(encryptedSummonerId);
    }
}
