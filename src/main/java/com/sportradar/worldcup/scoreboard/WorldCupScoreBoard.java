package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.model.Team;

import java.util.List;

/**
 * This class @{@link WorldCupScoreBoard} is considered as the interface for the live Football
 * WorldCup Scoreboard library, it also supports overloading for the operations
 * to make it easy to use by the other application (developers)
 * **/
public interface WorldCupScoreBoard {

    void startNewMatch(Math newMatch);

    void startNewMatch(Team homeTeam, Team awayTeam);

    void startNewMatch(String homeTeam, String awayTeam);

    void updateMatchScore(Math match);

    void updateMatchScore(Team homeTeam, Team awayTeam);

    void finishMatch(Match match);

    void finishMatch(Team homeTeam, Team awayTeam);

    void finishMatch(String homeTeam, String awayTeam);

    List<Match> getSummary();


}
