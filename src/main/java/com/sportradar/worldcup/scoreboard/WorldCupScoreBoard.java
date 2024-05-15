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

    /**
     * This function accept 1 params @match
     * and Finish the match, remove it from score board
     * It will return false if any of the match [ teams, names] are empty or null
     * It will throw @{@link MatchNotExistException} if the match not exist in the score board
     * if match finished/removed successfully it will return true
     *
     * @param match The match object with teams' names (scores will be ignored in this case).
     * @return True if match finished/removed, false otherwise or throw {@link MatchNotExistException} if match not exists.
     */
    boolean finishMatch(Match match) throws MatchNotExistException;

    /**
     * This function accept 2 params @homeTeam, @awayTeam
     * and Finish the match, remove it from score board
     * It will return false if any of the [teams, names] are empty or null
     * It will throw @{@link MatchNotExistException} if the match not exist in the score board
     * if match finished/removed successfully it will return true
     *
     * @param homeTeam The first team object (scores will be ignored in this case).
     * @param awayTeam The second team object (scores will be ignored in this case).
     * @return True if match finished/removed, false otherwise or throw {@link MatchNotExistException} if match not exists.
     */
    boolean finishMatch(Team homeTeam, Team awayTeam) throws MatchNotExistException;

    /**
     * This function accept 2 params @homeTeam, @awayTeam
     * and Finish the match, remove it from score board
     * It will return false if any of the teams' names are empty or null
     * It will throw @{@link MatchNotExistException} if the match not exist in the score board
     * if match finished/removed successfully it will return true
     *
     * @param homeTeam The first team name.
     * @param awayTeam The second team name.
     * @return True if match finished/removed, false otherwise or throw {@link MatchNotExistException} if match not exists.
     */
    boolean finishMatch(String homeTeam, String awayTeam) throws MatchNotExistException;

    /**
     * This function Get a summary of matches in progress ordered by their total score
     * The matches with the same total score will be returned ordered by the most recently started match in the scoreboard.
     * <p>
     * Note: we keep the Original list in the storage the same, and take a copy of the list,
     * so we don't change the order of matches started, in case of having 2 matches with same total scores
     *
     * @return @{@link List<Match>} ordered by their total score
     */
    List<Match> getSummary();


}
