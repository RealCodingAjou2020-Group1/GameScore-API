package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SoloRankInfo {
    private String leagueId;
    private String queueType;
    //private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    //private boolean inactive;
    //private boolean freshBlood;
    //private boolean hotStreak;
}
