package org.ajou.realcoding.lolapi.repository;

import org.ajou.realcoding.lolapi.domain.MatchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CurrentScoreRepository {
    @Autowired
    private MongoTemplate mongoTemplate;



    public MatchData insertOrUpdatedCurrentMatchData(MatchData currentMatchData) {
        return mongoTemplate.save(currentMatchData);
    }

    public MatchData findCurrentMatchDataByGameId(long gameId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameId").is(gameId));

        return mongoTemplate.findOne(query, MatchData.class);
    }
}
