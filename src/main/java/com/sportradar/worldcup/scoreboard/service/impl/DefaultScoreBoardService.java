package com.sportradar.worldcup.scoreboard.service.impl;

import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
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
    public boolean startNewMatch(String homeTeam, String awayTeam) throws TeamAlreadyPlayingException {
        if (isEmpty(homeTeam) || isEmpty(awayTeam)) {
            return false;
        }
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        for (Match match : matchList) {
            if (match.hasTeam(homeTeam) && match.hasTeam(awayTeam)) {
                throw new TeamAlreadyPlayingException("This Match already started before!");
            }
            if (match.hasTeam(homeTeam)) {
                throw new TeamAlreadyPlayingException(homeTeam + " Team already playing!");
            }
            if (match.hasTeam(awayTeam)) {
                throw new TeamAlreadyPlayingException(awayTeam + " Team already playing!");
            }
        }

        return this.scoreBoardRepository.addMatch(new Match(homeTeam, 0, awayTeam, 0));
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

    private boolean isEmpty(String team) {
        return team == null || team.trim().isEmpty();
    }
}
