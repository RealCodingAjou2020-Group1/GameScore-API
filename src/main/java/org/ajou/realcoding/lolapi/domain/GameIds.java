package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class GameIds
{
    @Id
    private String accountId;
    private Date Time;
    private int startIndex;
    private int totalGames;
    private int endIndex;
    private List<MatchReferenceDto> matches;

    @Data
    public static class MatchReferenceDto
    {
        private long gameId;
    }
}
