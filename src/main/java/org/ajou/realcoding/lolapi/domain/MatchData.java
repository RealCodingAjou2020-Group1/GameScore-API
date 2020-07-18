package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class MatchData {
    @Id
    private long gameId;
    private List<ParticipantIdentityDto> participantIdentities;
    private int queueId;
    private String gameType;
    private long gameDuration;
    private List<TeamStatsDto> teams;
    private String platformId;
    private long gameCreation;
    private int seasonId;
    private String gameVision;
    private int mapId;
    private String gameMode;
    private List<ParticipantDto> participants;
}
