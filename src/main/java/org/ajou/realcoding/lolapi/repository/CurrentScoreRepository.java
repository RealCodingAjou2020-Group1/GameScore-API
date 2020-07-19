package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.UserInfo;
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
}
