package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @GetMapping("/LOL/games")
    public void saveUserInfo(@RequestParam String summonerName) {
        log.info("SUMMONER NAME: {}", summonerName);
        currentScoreService.saveUserInfo(summonerName);
    }

    @GetMapping("/LOL/games/User")
    public UserInfo findUserInfo(@RequestParam String summonerName) {
        return currentScoreService.getUserInfo(summonerName);
    }


}
