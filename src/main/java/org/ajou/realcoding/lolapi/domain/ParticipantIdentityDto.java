package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ParticipantIdentityDto {
    @Id
    private int participantId;
    private PlayerDto player;
}
