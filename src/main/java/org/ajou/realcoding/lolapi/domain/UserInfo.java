package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserInfo {
    @Id
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private Long revisionDate;
    private int summonerLevel;
}
