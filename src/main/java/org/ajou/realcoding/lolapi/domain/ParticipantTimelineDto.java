package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;

import java.util.Map;

@Data
public class ParticipantTimelineDto {
    @Id
    private int participantId;
    private Map<String, Double> csDiffPerMinDeltas;
    private Map<String, Double> damageTakenPerMinDeltas;
    private String role;
    private Map<String, Double> damageTakenDiffPerMinDeltas;
    private Map<String, Double> xpPerMinDeltas;
    private Map<String, Double> xpDiffPerMinDeltas;
    private String lane;
    private Map<String, Double> creepsPerMinDeltas;
    private Map<String, Double> goldPerMinDeltas;
}
