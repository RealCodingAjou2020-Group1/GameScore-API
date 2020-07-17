package org.ajou.realcoding.lolapi.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RuneDto {
    @Id
    private int runeId;
    private int rank;
}
