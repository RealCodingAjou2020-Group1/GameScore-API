package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PlayerDto {
    private int profileIcon;
    @Id
    private String accountId;
    private String matchHistoryUri;
    private String currentAccountId;
    private String summonerName;
    private String summonerId;
    private String platformId;
}
