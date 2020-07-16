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

    public void saveCurrentSummonerName(UserInfo userInfo){
        UserInfo saveInfo = mongoTemplate.save(userInfo);
        log.info("Saved: {}", saveInfo);
    }


    public UserInfo findUserInfoByName(String summonerName) {
        Query query = Query.query(
                Criteria.where("name").is(summonerName)
        );
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);
        log.info("USER INFO: {}", userInfo.toString());
        return userInfo;
    }
}
