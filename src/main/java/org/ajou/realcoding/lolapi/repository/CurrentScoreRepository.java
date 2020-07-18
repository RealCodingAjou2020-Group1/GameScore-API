package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.MatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CurrentScoreRepository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public MatchInfo findGameId(String accountId)
    {
        Query query = Query.query(Criteria.where("_id").is(accountId));

        MatchInfo matchInfo = mongoTemplate.find(query, MatchInfo.class);
        return matchInfo;
    }
}
