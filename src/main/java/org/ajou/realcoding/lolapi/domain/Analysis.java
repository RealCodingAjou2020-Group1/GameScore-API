package org.ajou.realcoding.lolapi.domain;


import lombok.Data;

@Data
public class Analysis {
    private String summonerName;
    private long gameId;
    private int participantId;
    private int teamId;
    private String win;
    private int championId;
    private int kill;
    private int death;
    private int assists;
}
