package org.ajou.realcoding.lolapi.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.GameIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CurrentScoreRepository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveGameId(GameIds gameIds)
    {
        GameIds save100GameId = mongoTemplate.save(gameIds);
        log.info("Saved : {}", gameIds);
    }
}