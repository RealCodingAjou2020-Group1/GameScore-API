package org.ajou.realcoding.lolapi.repository;


import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.*;
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

    public UserInfo findUserInfoBySummonerName(String summonerName) {
        Query query = Query.query(
                Criteria.where("name").is(summonerName)
        );
        log.info("Found userInfo : {}", summonerName);
        return mongoTemplate.findOne(query, UserInfo.class);
    }

    public void insertOrUpdateCurrentSoloRankInfo(List<SoloRankInfo> soloRankInfoList){
        for(SoloRankInfo soloRankInfo : soloRankInfoList){
            mongoTemplate.save(soloRankInfo);
            log.info("Saved: {}", soloRankInfo);
        }
    }

    //앞뒤에 어떤 문자나 문자열이 오든 summonerId에 들어있는 문자열이 포함되면 조회한다.
    public List<SoloRankInfo> findSoloRankInfoByEncryptedSummonerId(String summonerId) {
        Query query = Query.query(
                Criteria.where("summonerId").regex(summonerId, "i")
        );
        //log.info("USER INFO: {}", soloRankInfoList.toString());
        return mongoTemplate.find(query, SoloRankInfo.class);
    }

    public Result insertOrUpdatedCurrentResult(Result result){
        //log.info("New Result : {}", result);
        return mongoTemplate.save(result);
    }

    public Result findCurrentResultByAccountId(String accountId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(accountId));
        //log.info("Find Result By : {}", accountId);
        return mongoTemplate.findOne(query, Result.class);
    }

    public GameIds saveGameId(GameIds gameIds) {
        GameIds save100GameId = mongoTemplate.save(gameIds);
        log.info("Save : {}", gameIds);

        return mongoTemplate.save(save100GameId);
    }

    public GameIds findGameIdsByAccountId(String accountId) {
        Query query = Query.query(
                Criteria.where("accountId").is(accountId)
        );
        GameIds findGameId = mongoTemplate.findOne(query, GameIds.class);
        log.info("Exist GameID : {}", findGameId);
        return findGameId;
    }
}
