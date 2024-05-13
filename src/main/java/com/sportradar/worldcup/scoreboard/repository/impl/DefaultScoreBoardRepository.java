package com.sportradar.worldcup.scoreboard.repository.impl;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.ScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.storage.ScoreBoardDB;

import java.util.List;


public class DefaultScoreBoardRepository implements ScoreBoardRepository {

    private final List<Match> matchList = ScoreBoardDB.getDB();

    @Override
    public void addMatch(Match match) {

    }

    @Override
    public void UpdateMatchScore(Match matchToUpdate) {

    }

    @Override
    public List<Match> findAllMatches() {
        return matchList;
    }


    @Override
    public void removeMatch(Match match) {

    }
}
