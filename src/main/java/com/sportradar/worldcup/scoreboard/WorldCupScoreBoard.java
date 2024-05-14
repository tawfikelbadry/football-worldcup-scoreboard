package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.exception.MatchNotExistException;
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

    /**
     * This function accept 1 params @match
     * and update the match score for this match
     * It will return false if any of the match [ teams, names] are empty or null
     * It will return false if the provided score less than 0
     * It will throw @{@link MatchNotExistException} if the match not exist in the score board
     * if match score updated successfully it will return true
     *
     * @param match The match object with teams' names and scores.
     * @return True if match score updated, false otherwise or throw {@link MatchNotExistException} if match not exists.
     */
    boolean updateMatchScore(Match match) throws MatchNotExistException;

    /**
     * This function accept 2 params @homeTeam, @awayTeam
     * and update the match score for this match
     * It's overloading the function @updateMatchScore(@{@link Match}), so it's doing the same logic
     *
     * @param homeTeam The first team object with its name and score.
     * @param awayTeam The second team object with its name and score.
     * @return True if match score updated, false otherwise or throw {@link MatchNotExistException} if match not exists.
     */
    boolean updateMatchScore(Team homeTeam, Team awayTeam) throws MatchNotExistException;

    void finishMatch(Match match);

    void finishMatch(Team homeTeam, Team awayTeam);

    void finishMatch(String homeTeam, String awayTeam);

    List<Match> getSummary();


}
