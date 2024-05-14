package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.exception.MatchNotExistException;
import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.model.Team;
import com.sportradar.worldcup.scoreboard.service.ScoreBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("FootballWorldCupScoreBoard Tests")
@ExtendWith(MockitoExtension.class)
public class FootballWorldCupScoreBoardTest {

    @InjectMocks
    private FootballWorldCupScoreBoard scoreBoard;
    @Mock
    private ScoreBoardService scoreBoardService;

    @Test
    @DisplayName("Start NewMatch Successfully Test")
    void startNewMatchSuccessfullyTest() throws TeamAlreadyPlayingException {
        Mockito.when(scoreBoardService.startNewMatch("HTeam", "ATeam")).thenReturn(true);
        boolean started = this.scoreBoard.startNewMatch("HTeam", "ATeam");
        Assertions.assertTrue(started, "Failed to start match!");
    }

    @Test
    @DisplayName("Start NewMatch Failed Test")
    void startNewMatchFailedTest() throws TeamAlreadyPlayingException {
        Mockito.when(scoreBoardService.startNewMatch("HTeam", "ATeam")).thenReturn(false);
        boolean started = this.scoreBoard.startNewMatch("HTeam", "ATeam");
        Assertions.assertFalse(started, "Match shouldn't start!");
    }

    @Test
    @DisplayName("Start NewMatch Failed TeamAlreadyPlaying Test")
    void startNewMatchFailedTeamAlreadyPlayingTest() throws TeamAlreadyPlayingException {
        Mockito.when(scoreBoardService.startNewMatch("HTeam", "ATeam")).thenThrow(TeamAlreadyPlayingException.class);
        Assertions.assertThrows(TeamAlreadyPlayingException.class, () -> scoreBoard.startNewMatch("HTeam", "ATeam"));
    }

    @Test
    @DisplayName("Start NewMatch failed empty team Test")
    void startNewMatchFailedEmptyTeamTest() throws TeamAlreadyPlayingException {
        Mockito.when(scoreBoardService.startNewMatch("", "ATeam")).thenReturn(false);
        boolean started = this.scoreBoard.startNewMatch("", "ATeam");
        Assertions.assertFalse(started, "Match shouldn't start!");
    }

    @Test
    @DisplayName("Update MatchScore Successfully Test")
    void updateMatchScoreSuccessfulTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 2, "ATeam", 1);
        Mockito.when(scoreBoardService.updateMatchScore(match)).thenReturn(true);
        boolean updated = this.scoreBoard.updateMatchScore(match);
        Assertions.assertTrue(updated, "Failed to update match score!");

        Team homeTeam = Team.builder().name("HomeTeam").score(2).build();
        Team awayTeam = Team.builder().name("AwayTeam").score(1).build();
        Mockito.when(scoreBoardService.updateMatchScore(ArgumentMatchers.any(Match.class))).thenReturn(true);
        updated = this.scoreBoard.updateMatchScore(homeTeam, awayTeam);
        Assertions.assertTrue(updated, "Failed to update match score!");

    }

    @Test
    @DisplayName("Update MatchScore Failed Test")
    void updateMatchScoreFailedTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 2, "ATeam", 1);
        Mockito.when(scoreBoardService.updateMatchScore(match)).thenThrow(new MatchNotExistException("Match is not exist!"));
        MatchNotExistException exception = Assertions.assertThrows(MatchNotExistException.class, () -> scoreBoard.updateMatchScore(match));
        Assertions.assertEquals("Match is not exist!", exception.getMessage(), "Exception message is wrong!");
    }

    @Test
    @DisplayName("Finish Match Successfully Test")
    void finishMatchSuccessfulTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 2, "ATeam", 1);
        Mockito.when(scoreBoardService.finishMatch(match)).thenReturn(true);
        boolean finished = this.scoreBoard.finishMatch(match);
        Assertions.assertTrue(finished, "Failed to finish match!");

        Team homeTeam = Team.builder().name("HomeTeam").score(2).build();
        Team awayTeam = Team.builder().name("AwayTeam").score(1).build();
        Mockito.when(scoreBoardService.finishMatch(ArgumentMatchers.any(Match.class))).thenReturn(true);
        finished = this.scoreBoard.finishMatch(homeTeam, awayTeam);
        Assertions.assertTrue(finished, "Failed to finish match!");
    }

    @Test
    @DisplayName("Update MatchScore Failed Test")
    void finishMatchFailedTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 2, "ATeam", 1);
        Mockito.when(scoreBoardService.finishMatch(match)).thenThrow(new MatchNotExistException("Match is not exist!"));
        MatchNotExistException exception = Assertions.assertThrows(MatchNotExistException.class, () -> scoreBoard.finishMatch(match));
        Assertions.assertEquals("Match is not exist!", exception.getMessage(), "Exception message is wrong!");
    }

}
