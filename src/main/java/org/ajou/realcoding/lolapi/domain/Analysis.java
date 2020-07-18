package org.ajou.realcoding.lolapi.domain;

import lombok.Data;

@Data
public class Analysis {
    int participantId;
    int teamId;
    String win;
    int championId;
    int kill;
    int death;
    int assists;
}
