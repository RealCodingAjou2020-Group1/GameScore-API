package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @GetMapping("lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    public List<SoloRankInfo> getSoloRankInfo(@PathVariable String encryptedSummonerId) {
        return currentScoreService.getSoloRankInfo(encryptedSummonerId);
    }

}
