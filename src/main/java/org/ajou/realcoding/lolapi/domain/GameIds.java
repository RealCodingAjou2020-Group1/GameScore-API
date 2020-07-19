package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class GameIds
{
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
