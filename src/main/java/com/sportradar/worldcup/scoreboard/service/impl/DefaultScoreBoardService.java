package com.sportradar.worldcup.scoreboard.service.impl;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.ScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.repository.impl.DefaultScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.service.ScoreBoardService;

import java.util.List;

public class DefaultScoreBoardService implements ScoreBoardService {

    private final ScoreBoardRepository scoreBoardRepository;

    public DefaultScoreBoardService() {
        this.scoreBoardRepository = new DefaultScoreBoardRepository();
    }

    public DefaultScoreBoardService(ScoreBoardRepository scoreBoardRepository) {
        this.scoreBoardRepository = scoreBoardRepository;
    }

    @Override
    public void startNewMatch(Math newMatch) {

    }

    @Override
    public void updateMatchScore(Math match) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public List<Match> getSummary() {
        return null;
    }
}
