package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class GameIds {
    @Id
    private String accountId;
    private int startIndex;
    private int totalGames;
    private int endIndex;
    private List<MatchReferenceDto> matches;
    private Date time;

    @Data
    public static class MatchReferenceDto
    {
        private long gameId;
    }
}
