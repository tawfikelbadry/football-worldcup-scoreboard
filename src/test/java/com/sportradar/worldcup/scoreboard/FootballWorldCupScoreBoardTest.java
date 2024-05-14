package com.sportradar.worldcup.scoreboard;

import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.service.ScoreBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

}
