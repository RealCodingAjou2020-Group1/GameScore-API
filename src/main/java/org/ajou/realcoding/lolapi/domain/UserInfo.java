package org.ajou.realcoding.lolapi.domain;

import org.springframework.data.annotation.Id;

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
