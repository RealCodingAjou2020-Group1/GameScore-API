package org.ajou.realcoding.lolapi.controller;

import jdk.nashorn.internal.runtime.regexp.joni.MatcherFactory;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.Analysis;
import org.ajou.realcoding.lolapi.domain.GameIds;
import org.ajou.realcoding.lolapi.domain.MatchData;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CurrentScoreController {
    @Autowired
    private CurrentScoreService currentScoreService;

    private List<Analysis> result = new ArrayList<>();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/games/{summonerName}")
    public List<Analysis> getAnalysis(@RequestParam String summonerName) {
        //String accountId = "3CvKgEoNw43RLWlbVTEiIv2OSd5sfMURYMs2iBQO-f0b";
        String accountId = "G-Dzk101eUvGY_sv183fa9z5DGbRxITFGR6zb-eRZmMhtDo";

        return currentScoreService.getAnalysis(currentScoreService.getFiveGameId(accountId), summonerName);
    }
}
