package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.Analysis;
import org.ajou.realcoding.lolapi.domain.GameIds;
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

    public Analysis insertOrUpdatedCurrentResult(Analysis analysis) {
        //log.info("CurrentMatchData has inserted or updated successfully. CurrentMatchData : {}", currentMatchData);
        return mongoTemplate.save(analysis);
    }

    public Analysis findCurrentAnalysisByGameIdAndName(long gameId, String summonerName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gameId").is(gameId));
        log.info("Find  : {}", gameId);
        query.addCriteria(Criteria.where("summonerName").is(summonerName));
        log.info("Find  : {}", summonerName);
        return mongoTemplate.findOne(query, Analysis.class);
    }

    public GameIds saveGameId(GameIds gameIds) {
        GameIds save100GameId = mongoTemplate.save(gameIds);
        log.info("Saved : {}", gameIds);

        return save100GameId;
    }
}
