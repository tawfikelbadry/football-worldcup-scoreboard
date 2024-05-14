package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.model.Team;

import java.util.List;

/**
 * This class @{@link WorldCupScoreBoard} is considered as the interface for the live Football
 * WorldCup Scoreboard library, it also supports overloading for the operations
 * to make it easy to use by the other application (developers)
 **/
public interface WorldCupScoreBoard {

    /**
     * This function accept 2 String params @homeTeam, @awayTeam
     * and create a new match with this 2 teams, both teams will have score of 0
     * It will return false if any of the match names are empty or null
     * It will throw @{@link TeamAlreadyPlayingException} if one of teams or both are playing with related message
     * if match created successfully it will return true
     *
     * @param homeTeam The first team.
     * @param awayTeam The second team.
     * @return True if match created, false otherwise or throw {@link TeamAlreadyPlayingException} if teams exist.
     */
    boolean startNewMatch(String homeTeam, String awayTeam) throws TeamAlreadyPlayingException;

    void updateMatchScore(Math match);

    void updateMatchScore(Team homeTeam, Team awayTeam);

    void finishMatch(Match match);

    void finishMatch(Team homeTeam, Team awayTeam);

    void finishMatch(String homeTeam, String awayTeam);

    List<Match> getSummary();


}
