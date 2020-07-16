package org.ajou.realcoding.lolapi.domain;

import lombok.Data;

@Data
public class SoloRankInfo {
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
}
