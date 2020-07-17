package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class CurrentScoreRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveCurrentSummonerName(SoloRankInfo soloRankInfo){
        SoloRankInfo saveQueueType = mongoTemplate.save(soloRankInfo);
        log.info("Saved: {}", saveQueueType);
    }


    public SoloRankInfo findUserInfoByName(String encryptedSummonerId) {
        Query query = Query.query(
                Criteria.where("summonerId").regex(encryptedSummonerId, "i")
        );
        SoloRankInfo soloRankInfo = mongoTemplate.findOne(query, SoloRankInfo.class);
        log.info("USER INFO: {}", soloRankInfo.toString());
        return soloRankInfo;
    }
}
