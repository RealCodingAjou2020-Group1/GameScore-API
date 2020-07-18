package org.ajou.realcoding.lolapi.service;

import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId) {
        List<SoloRankInfo> soloRankInfo = scoreOpenApiClient.getSoloRankInfo(encryptedSummonerId);
        currentScoreRepository.saveSoloRankInfo(soloRankInfo);
        return currentScoreRepository.findUserInfoByName(encryptedSummonerId);
    }
}
