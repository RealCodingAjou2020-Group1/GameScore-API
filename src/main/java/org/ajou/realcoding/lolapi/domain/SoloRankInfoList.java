package org.ajou.realcoding.lolapi.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SoloRankInfoList {

    private List<SoloRankInfo> soloRankInfoList;

    public SoloRankInfoList() {
        soloRankInfoList = new ArrayList<>();
    }
}
