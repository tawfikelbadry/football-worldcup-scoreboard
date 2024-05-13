package com.sportradar.worldcup.scoreboard.repository;


import com.sportradar.worldcup.scoreboard.model.Match;

import java.util.List;

/**
 * This repository offers the functionality of interacting with the storage layer,
 * it basically supports the Crud Operation without handling any business logic
 **/
public interface ScoreBoardRepository {

    boolean addMatch(Match match);

    boolean updateMatch(Match match);

    List<Match> findAllMatches();

    boolean removeMatch(Match match);

    void clearAll();

}
