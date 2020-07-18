package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.MatchData;
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

    public MatchData insertOrUpdatedCurrentMatchData(MatchData currentMatchData) {
        //log.info("CurrentMatchData has inserted or updated successfully. CurrentMatchData : {}", currentMatchData);
        return mongoTemplate.save(currentMatchData);
    }

    public MatchData findCurrentMatchDataByGameId(long gameId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameId").is(gameId));
        //log.info("Find CurrentMatchData : {}", gameId);
        return mongoTemplate.findOne(query, MatchData.class);
    }
}
