package org.ajou.realcoding.lolapi.controller;

import jdk.nashorn.internal.runtime.regexp.joni.MatcherFactory;
import org.ajou.realcoding.lolapi.domain.MatchData;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/match/v4/matches/{matchId}")
    public MatchData getCurrentMatchDataByGameId(@PathVariable long gameId) {
        return currentScoreService.getCurrentMatchDataByGameId(gameId);
    }
}
