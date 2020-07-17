package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PraticipantIdentityDto {
    @Id
    private int participantId;
    private PlayerDto player;
}
