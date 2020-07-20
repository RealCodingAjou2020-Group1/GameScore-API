package org.ajou.realcoding.lolapi.controller;

import org.ajou.realcoding.lolapi.service.CurrentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrentScoreController {

    @Autowired
    private CurrentScoreService currentScoreService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    public List<SoloRankInfo> getSoloRankInfo(@RequestParam String summonerName) {
        return currentScoreService.getSoloRankInfo(currentScoreService.getUserInfo(summonerName).getId());
    }

    private List<Analysis> result = new ArrayList<>();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/lol/games/{summonerName}")
    public List<Analysis> getAnalysis(@RequestParam String summonerName) {
        //String accountId = "3CvKgEoNw43RLWlbVTEiIv2OSd5sfMURYMs2iBQO-f0b";
        String accountId = "G-Dzk101eUvGY_sv183fa9z5DGbRxITFGR6zb-eRZmMhtDo";

        return currentScoreService.getAnalysis(currentScoreService.getFiveGameId(accountId), summonerName);
    }
}
