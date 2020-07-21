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

    //parameter 로 들어온 userInfo 를 MongoDB에 저장
    public UserInfo insertOrUpdatedCurrentUserInfo(UserInfo userInfo){
        return mongoTemplate.save(userInfo);
    }

    //summonerName 으로 DB 에서 해당 값 찾아서 return
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

    //전달받은 result 객체를 DB에 저장
    public Result insertOrUpdatedCurrentResult(Result result){
        //log.info("New Result : {}", result);
        return mongoTemplate.save(result);
    }
    //accountID를 key값으로 하는 Result DB에 검색하여 return
    public Result findCurrentResultByAccountId(String accountId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(accountId));
        //log.info("Find Result By : {}", accountId);
        return mongoTemplate.findOne(query, Result.class);
    }

    //100개의 gameIds를 DB에 저장.
    public GameIds saveGameId(GameIds gameIds) {
        GameIds save100GameId = mongoTemplate.save(gameIds);
        log.info("Save : {}", gameIds);

        return mongoTemplate.save(save100GameId);
    }

    //accountId를 이용한 query로 DB 탐색 후, log 출력 및 탐색결과 return.
    public GameIds findGameIdsByAccountId(String accountId) {
        Query query = Query.query(
                Criteria.where("accountId").is(accountId)
        );
        GameIds findGameId = mongoTemplate.findOne(query, GameIds.class);
        log.info("Exist GameID : {}", findGameId);
        return findGameId;
    }
}
