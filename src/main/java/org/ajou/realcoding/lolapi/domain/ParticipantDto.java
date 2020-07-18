package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class ParticipantDto {
    @Id
    private int participantId;
    private int championId;
    private List<RuneDto> runes;
    private ParticipantStatsDto stats;
    private int teamId;
    private ParticipantTimelineDto timeline;
    private int spell1Id;
    private int spell2Id;
    private String highestAchievedSeasonTier;
    private List<MasteryDto> masteries;
}
