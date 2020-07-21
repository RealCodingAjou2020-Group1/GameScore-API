package org.ajou.realcoding.lolapi.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Result {
    @Id
    private String accountId;
    private List<Analysis> analysisList;

}
