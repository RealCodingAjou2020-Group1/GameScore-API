package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TeamBansDto {
    private int championId;
    private int pickTurn;
}
