package org.ajou.realcoding.lolapi.domain;

import lombok.Data;

import java.util.List;

@Data
public class GameIds
{
    List<MatchReferenceDto> matches;

    public static class MatchReferenceDto
    {
        private long gameId;
    }
}
