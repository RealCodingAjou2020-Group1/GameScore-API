package org.ajou.realcoding.lolapi.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CurrentScoreService {
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    public UserInfo getUserInfo(String summonerName) {
        UserInfo currentUserInfo = scoreOpenApiClient.getUserInfo(summonerName);

        UserInfo currentUserInfoFromDb = currentScoreRepository.findUserInfoByName(summonerName);

        if(currentUserInfoFromDb == null) { //id가 갖지 않은 경우 추가
            UserInfo insertedOrUpdatedCurrentUserInfo = currentScoreRepository.insertOrUpdatedCurrentUserInfo(currentUserInfo);
            log.info("CurrentUserInfo has inserted or updated successfully. UserInfo : {}", insertedOrUpdatedCurrentUserInfo);
            return insertedOrUpdatedCurrentUserInfo;
        }
        log.info("Already exists. CurrentUserInfo : {}", currentUserInfoFromDb);
        return currentUserInfoFromDb;
    }


}
