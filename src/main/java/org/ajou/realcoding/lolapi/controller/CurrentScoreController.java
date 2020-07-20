package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.Analysis;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public List<Analysis> getAnalysis(@RequestParam String summonerName) {
        currentScoreService.getSoloRankInfo(currentScoreService.getUserInfo(summonerName).getId());
        return currentScoreService.getAnalysis(currentScoreService.getFiveGameId(currentScoreService.getGameId(currentScoreService.getUserInfo(summonerName).getAccountId())), summonerName);
    }

}
