package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j
public class CurrentScoreRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public UserInfo insertOrUpdatedCurrentUserInfo(UserInfo userInfo){
        return mongoTemplate.save(userInfo);
    }


    public UserInfo findUserInfoByName(String summonerName) {

        Query query = Query.query(
                Criteria.where("name").is(summonerName)
        );
        //log.info("Found userInfo : {}", summonerName);
        return mongoTemplate.findOne(query, UserInfo.class);
    }

    public void insertOrUpdateCurrentSoloRankInfo(List<SoloRankInfo> soloRankInfoList){
        for(SoloRankInfo soloRankInfo : soloRankInfoList){
            mongoTemplate.save(soloRankInfo);
            log.info("Saved: {}", soloRankInfo);
        }
    }


    public List<SoloRankInfo> findSoloRankInfo(String encryptedSummonerId) {
        Query query = Query.query(
                Criteria.where("summonerId").regex(encryptedSummonerId, "i")
        );
        List<SoloRankInfo> soloRankInfoList = mongoTemplate.find(query, SoloRankInfo.class);
        log.info("USER INFO: {}", soloRankInfoList.toString());
        return soloRankInfoList;
    }
}
