package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.SoloRankInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public List<SoloRankInfo> getSoloRankInfo(@RequestParam String summonerName) {
        return currentScoreService.getSoloRankInfo(currentScoreService.getUserInfo(summonerName).getId());
    }

}
