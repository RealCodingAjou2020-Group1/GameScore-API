package org.ajou.realcoding.lolapi.repository;

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

    public void insertOrUpdateCurrentSoloRankInfo(List<SoloRankInfo> soloRankInfoList){
        for(SoloRankInfo soloRankInfo : soloRankInfoList){
            mongoTemplate.save(soloRankInfo);
            log.info("Saved: {}", soloRankInfo);
        }
    }


    public List<SoloRankInfo> findSoloRankInfo(String encryptedSummonerId) {
        Query query = Query.query(
                Criteria.where("summonerId").regex(encryptedSummonerId, "i")
        );
        //log.info("USER INFO: {}", soloRankInfoList.toString());
        return mongoTemplate.find(query, SoloRankInfo.class);
    }

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
