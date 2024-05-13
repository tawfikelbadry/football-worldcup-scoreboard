package com.sportradar.worldcup.scoreboard.repository;


import com.sportradar.worldcup.scoreboard.model.Match;

import java.util.List;

/**
 * This repository offers the functionality of interacting with the storage layer,
 * it basically supports the Crud Operation without handling any business logic
 **/
public interface ScoreBoardRepository {

    void addMatch(Match match);

    void UpdateMatchScore(Match match);

    List<Match> findAllMatches();

    void removeMatch(Match match);

}
