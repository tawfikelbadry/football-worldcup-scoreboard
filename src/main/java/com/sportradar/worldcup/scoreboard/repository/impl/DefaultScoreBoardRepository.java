package com.sportradar.worldcup.scoreboard.repository.impl;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.ScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.storage.ScoreBoardDB;

import java.util.List;


public class DefaultScoreBoardRepository implements ScoreBoardRepository {

    private final List<Match> matchList = ScoreBoardDB.getDB();

    @Override
    public boolean addMatch(Match match) {
        if (exists(match)) {
            return false;
        }
        this.matchList.add(match);
        return true;
    }

    @Override
    public boolean updateMatch(Match matchToUpdate) {
        for (Match match : this.matchList) {
            if (isSameMatch(match, matchToUpdate)) {
                match.setHomeTeam(matchToUpdate.getHomeTeam());
                match.setAwayTeam(matchToUpdate.getAwayTeam());
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Match> findAllMatches() {
        return matchList;
    }


    @Override
    public boolean removeMatch(Match matchToDelete) {
        return this.matchList.removeIf(match -> isSameMatch(matchToDelete, match));
    }

    @Override
    public void clearAll() {
        this.matchList.clear();
    }

    @Override
    public boolean exists(Match match) {
        return !this.matchList.stream()
                .filter(match2 -> isSameMatch(match, match2)).toList().isEmpty();
    }

    private boolean isSameMatch(Match match, Match matchToUpdate) {
        return match.getHomeTeam().getName().equals(matchToUpdate.getHomeTeam().getName()) &&
                match.getAwayTeam().getName().equals(matchToUpdate.getAwayTeam().getName());
    }

}
