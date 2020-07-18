package org.ajou.realcoding.lolapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/games/{summonerName}")
    public UserInfo saveUserInfo(@RequestParam String summonerName) {
        return currentScoreService.getUserInfo(summonerName);
    }


}
