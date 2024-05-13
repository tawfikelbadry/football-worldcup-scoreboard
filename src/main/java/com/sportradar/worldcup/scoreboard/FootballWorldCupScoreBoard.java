package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.model.Team;
import com.sportradar.worldcup.scoreboard.service.ScoreBoardService;
import com.sportradar.worldcup.scoreboard.service.impl.DefaultScoreBoardService;

import java.util.List;

public class FootballWorldCupScoreBoard implements WorldCupScoreBoard {

    private final ScoreBoardService scoreBoardService;

    public FootballWorldCupScoreBoard() {
        this.scoreBoardService = new DefaultScoreBoardService();
    }

    public FootballWorldCupScoreBoard(ScoreBoardService scoreBoardService) {
        this.scoreBoardService = scoreBoardService;
    }

    @Override
    public void startNewMatch(Math newMatch) {

    }

    @Override
    public void startNewMatch(Team homeTeam, Team awayTeam) {

    }

    @Override
    public void startNewMatch(String homeTeam, String awayTeam) {

    }

    @Override
    public void updateMatchScore(Math match) {

    }

    @Override
    public void updateMatchScore(Team homeTeam, Team awayTeam) {

    }

    @Override
    public void finishMatch(Match match) {

    }

    @Override
    public void finishMatch(Team homeTeam, Team awayTeam) {

    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {

    }

    @Override
    public List<Match> getSummary() {
        return null;
    }
}
