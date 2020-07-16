package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MatchInfo {
    private String platformId;
    @Id
    private String champion;
    private String queue;
    private String season;
    private String timeStamp;
    private String role;
    private String lane;
}
