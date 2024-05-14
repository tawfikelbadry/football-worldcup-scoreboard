package com.sportradar.worldcup.scoreboard.service;

import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.model.Match;

import java.util.List;

/**
 * This service offers the functionality of world cup scoreboard with all it's operations,
 * it handles the business logic for the football world cup scoreboard
 * It gets the data from the repository layer
 **/
public interface ScoreBoardService {

    boolean startNewMatch(String homeTeam, String awayTeam) throws TeamAlreadyPlayingException;

    void updateMatchScore(Math match);

    void finishMatch(Match match);

    List<Match> getSummary();

}
