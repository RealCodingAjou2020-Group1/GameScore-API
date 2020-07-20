package org.ajou.realcoding.lolapi.service;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.GameIds;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Slf4j
public class CurrentScoreService
{
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    private int cnt = -1;
    private List<String> saveAccountId = new ArrayList<>();

    public void timer()
    {
        cnt = 0;
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                cnt = 1;
            }
        };
        timer.schedule(timerTask, 1200000);
    }

    @GetMapping("/lol/match/v4/matchlists/by-account")
    public GameIds getGameId(String accountId)
    {// && saveAccountId.contains(accountId) == true)
        if(cnt == -1 || cnt == 1)//처음, 20분O
        {
            saveAccountId.add(accountId);
            timer();
            GameIds gameIds = scoreOpenApiClient.getGameId(accountId);
            currentScoreRepository.saveGameId(gameIds);
            log.info("Save!");
            return gameIds;
        }
        else//20분X
        {
            GameIds findGameIds = currentScoreRepository.findGameIds(accountId);
            log.info("Find!");
            return findGameIds;
        }
    }
}
