package com.sportradar.worldcup.scoreboard.service.impl;

import com.sportradar.worldcup.scoreboard.exception.MatchNotExistException;
import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.ScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.repository.impl.DefaultScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.service.ScoreBoardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultScoreBoardService implements ScoreBoardService {

    private static final Logger logger = LogManager.getLogger(DefaultScoreBoardService.class);

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
            logger.warn("One of your teams' values are empty, Skipping and returning false");
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
    public boolean updateMatchScore(Match match) throws MatchNotExistException {
        if (!validateMatch(match)) {
            return false;
        }
        if (match.getHomeTeam().getScore() < 0 || match.getAwayTeam().getScore() < 0) {
            logger.warn("Teams' scores can't be with negative, Skipping and returning false");
            return false;
        }
        if (!scoreBoardRepository.exists(match)) {
            throw new MatchNotExistException("Match is not exist!");
        }
        return scoreBoardRepository.updateMatch(match);
    }

    @Override
    public boolean finishMatch(Match match) throws MatchNotExistException {
        if (!validateMatch(match)) {
            return false;
        }
        if (!scoreBoardRepository.exists(match)) {
            throw new MatchNotExistException("Match is not exist!");
        }
        return scoreBoardRepository.removeMatch(match);
    }

    @Override
    public List<Match> getSummary() {
        List<Match> orderedMatchList = new ArrayList<>(this.scoreBoardRepository.findAllMatches());
        orderedMatchList.sort(
                Comparator.comparingInt(match -> (match.getHomeTeam().getScore() + match.getAwayTeam().getScore()))
        );
        return orderedMatchList.reversed();
    }

    private boolean isEmpty(String team) {
        return team == null || team.trim().isEmpty();
    }

    private boolean validateMatch(Match match) {
        if (match == null) {
            logger.warn("Match object is null, Skipping and returning false");
            return false;
        } else if (match.getHomeTeam() == null || match.getAwayTeam() == null) {
            logger.warn("one Team object or more are null, Skipping and returning false");
            return false;
        } else if (isEmpty(match.getHomeTeam().getName()) || isEmpty(match.getAwayTeam().getName())) {
            logger.warn("One of your teams' values are empty, Skipping and returning false");
            return false;
        }
        return true;
    }
}
