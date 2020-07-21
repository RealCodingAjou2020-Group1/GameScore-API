package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MatchInfo {
    @Id
    private long gameId;
    private String platformId;
    private int champion;
    private int queue;
    private int season;
    private long timeStamp;
    private String role;
    private String lane;
}
