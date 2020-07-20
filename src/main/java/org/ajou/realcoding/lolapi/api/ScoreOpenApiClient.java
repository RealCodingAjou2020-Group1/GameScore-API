package org.ajou.realcoding.lolapi.api;

public class ScoreOpenApiClient {
    @Autowired
    private RestTemplate restTemplate;

    private final String apiKey = "RGAPI-98bfacd4-e4af-41f7-a54a-58905deaad50";
    private final String matchDataUri = "https://kr.api.riotgames.com/lol/match/v4/matches/{gameId}?api_key={apiKey}";

    private static final String REQUEST_URI = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-12220222-ebfc-4eaa-af12-f9f055506df9";
    private static final String QUEUE_TYPE_REQUEST_URI = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-12220222-ebfc-4eaa-af12-f9f055506df9";

    private static final String GAMEID_REQUEST_URI = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{accountId}?api_key={apiKey}";

    public MatchData getMatchData(String gameId) {
        return restTemplate.getForObject(matchDataUri, MatchData.class, gameId, apiKey);
    }

    public GameIds getGameId(String accountId)
    {
        GameIds gameIds = restTemplate.getForObject(GAMEID_REQUEST_URI, GameIds.class, accountId, apiKey);
        return gameIds;
    }

    public UserInfo getUserInfo(String summonerName){
        log.info("Requesting to API ...");
        return restTemplate.getForObject(REQUEST_URI, UserInfo.class, summonerName);
    }

    public List<SoloRankInfo> getSoloRankInfo(String encryptedSummonerId){
        SoloRankInfo[] response = restTemplate.getForObject(QUEUE_TYPE_REQUEST_URI, SoloRankInfo[].class, encryptedSummonerId);
        List<SoloRankInfo> soloRankInfoList = Arrays.asList(response);
        for (SoloRankInfo soloRankInfo : soloRankInfoList) {
            log.info(soloRankInfo.toString());
        }

        return soloRankInfoList;
    }
}
