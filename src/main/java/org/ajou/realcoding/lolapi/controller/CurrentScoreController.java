package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.Analysis;
import org.ajou.realcoding.lolapi.domain.Result;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/RecentFiveGameResults/{summonerName}")
    public Result getResult(@RequestParam String summonerName) throws ParseException {
        UserInfo userInfo = currentScoreService.getUserInfo(summonerName);
        currentScoreService.getSoloRankInfo(userInfo.getId());
        return currentScoreService.getAnalysis(currentScoreService.getFiveGameId(currentScoreService.getGameId(userInfo.getAccountId())), userInfo.getAccountId(), summonerName);
    }
}
