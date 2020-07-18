package org.ajou.realcoding.lolapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ajou.realcoding.lolapi.api.ScoreOpenApiClient;
import org.ajou.realcoding.lolapi.domain.MatchInfo;
import org.ajou.realcoding.lolapi.domain.UserInfo;
import org.ajou.realcoding.lolapi.repository.CurrentScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.Queue;

@Service
@EnableScheduling
public class CurrentScoreService
{
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ScoreOpenApiClient scoreOpenApiClient;

    private String accountId1;

    private Queue<Long> gameIdQueue = new LinkedList<>();
    @Autowired
    private CurrentScoreRepository currentScoreRepository;

    @GetMapping("/lol/match/v4/matchlists/by-account")
    public MatchInfo getGameId(String accountId)
    {
        accountId1 = accountId;
        return currentScoreRepository.findGameId(accountId);
    }

    @Scheduled(fixedDelay = 5000L)
    public void getCurrentGameIdEveryFiveSeconds()
    {
        MatchInfo matchInfo = scoreOpenApiClient.getGameId(accountId1);
        currentScoreRepository.saveGameId(matchInfo);
    }

}
