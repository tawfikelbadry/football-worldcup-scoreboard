package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.exception.MatchNotExistException;
import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
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
    public boolean startNewMatch(String homeTeam, String awayTeam) throws TeamAlreadyPlayingException {
        return this.scoreBoardService.startNewMatch(homeTeam, awayTeam);
    }

    @Override
    public boolean updateMatchScore(Match match) throws MatchNotExistException {
        return this.scoreBoardService.updateMatchScore(match);
    }

    @Override
    public boolean updateMatchScore(Team homeTeam, Team awayTeam) throws MatchNotExistException {
        return this.updateMatchScore(new Match(homeTeam, awayTeam));
    }

    @Override
    public boolean finishMatch(Match match) throws MatchNotExistException {
        return this.scoreBoardService.finishMatch(match);
    }

    @Override
    public boolean finishMatch(Team homeTeam, Team awayTeam) throws MatchNotExistException {
        return this.finishMatch(new Match(homeTeam, awayTeam));
    }

    @Override
    public boolean finishMatch(String homeTeam, String awayTeam) throws MatchNotExistException {
        return this.finishMatch(new Match(homeTeam, awayTeam));
    }

    @Override
    public List<Match> getSummary() {
        return this.scoreBoardService.getSummary();
    }
}
