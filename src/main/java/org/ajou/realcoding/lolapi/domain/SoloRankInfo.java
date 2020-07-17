package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
<<<<<<<<< Temporary merge branch 1

@Data
public class SoloRankInfo {
=========
import org.springframework.data.annotation.Id;

@Data
public class SoloRankInfo {
    @Id
>>>>>>>>> Temporary merge branch 2
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
<<<<<<<<< Temporary merge branch 1
=========
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;
>>>>>>>>> Temporary merge branch 2
}
