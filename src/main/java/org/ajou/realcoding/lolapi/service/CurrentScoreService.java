package org.ajou.realcoding.lolapi.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId) {

        List<SoloRankInfo> currentSoloRankInfoFromDb = currentScoreRepository.findSoloRankInfo(encryptedSummonerId);

        if(currentSoloRankInfoFromDb.isEmpty()){
            List<SoloRankInfo> soloRankInfo = scoreOpenApiClient.getSoloRankInfo(encryptedSummonerId);
            currentScoreRepository.insertOrUpdateCurrentSoloRankInfo(soloRankInfo);
            currentSoloRankInfoFromDb = currentScoreRepository.findSoloRankInfo(encryptedSummonerId);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", currentSoloRankInfoFromDb);
        }
        log.info("Already exists. CurrentUserInfo : {}", currentSoloRankInfoFromDb);
        return currentSoloRankInfoFromDb;
    }
}
