package org.ajou.realcoding.lolapi.controller;

import jdk.nashorn.internal.runtime.regexp.joni.MatcherFactory;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.Analysis;
import org.ajou.realcoding.lolapi.domain.MatchData;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CurrentScoreController {
    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/match/v4/matches/{gameId}")
    public Analysis getCurrentMatchDataByGameId(@PathVariable String gameId) {
        return currentScoreService.analyzeMatchData(currentScoreService.getMatchData(gameId)) ;
    }
}
