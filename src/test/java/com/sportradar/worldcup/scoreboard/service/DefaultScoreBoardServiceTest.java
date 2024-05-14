package com.sportradar.worldcup.scoreboard.service;

import com.sportradar.worldcup.scoreboard.exception.TeamAlreadyPlayingException;
import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.impl.DefaultScoreBoardRepository;
import com.sportradar.worldcup.scoreboard.service.impl.DefaultScoreBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@DisplayName("DefaultScoreBoardService Tests")
@ExtendWith(MockitoExtension.class)
public class DefaultScoreBoardServiceTest {

    @Mock
    private DefaultScoreBoardRepository scoreBoardRepository;

    @InjectMocks
    private DefaultScoreBoardService scoreBoardService;

    @Test
    @DisplayName("Start NewMatch Failed EmptyTeamValue Test")
    void startNewMatchFailedEmptyTeamValueTest() throws TeamAlreadyPlayingException {
        boolean started = scoreBoardService.startNewMatch("", "ATeam");
        Assertions.assertFalse(started, "Team is empty");
        started = scoreBoardService.startNewMatch("HTeam", null);
        Assertions.assertFalse(started, "Team is null");
        started = scoreBoardService.startNewMatch("", null);
        Assertions.assertFalse(started, "Team is null");
    }

    @Test
    @DisplayName("Start NewMatch FailedAlreadyPlaying Test")
    void startNewMatchFailedAlreadyPlayingTest() {
        Mockito.when(scoreBoardRepository.findAllMatches())
                .thenReturn(List.of(new Match("HTeam", 1, "ATeam", 2)));

        TeamAlreadyPlayingException exception = Assertions.assertThrows(TeamAlreadyPlayingException.class
                , () -> this.scoreBoardService.startNewMatch("ATeam", "XTeam")
                , "Expected to throw Exception but it didn't!"
        );
        Assertions.assertEquals("ATeam Team already playing!", exception.getMessage()
                , "returned message from exception doesn't match the expected value!");


        exception = Assertions.assertThrows(TeamAlreadyPlayingException.class
                , () -> this.scoreBoardService.startNewMatch("LTeam", "HTeam")
                , "Expected to throw Exception but it didn't!"
        );
        Assertions.assertEquals("HTeam Team already playing!", exception.getMessage()
                , "returned message from exception doesn't match the expected value!");

    }

    @Test
    @DisplayName("Start NewMatch FailedAlreadyStarted Test")
    void startNewMatchFailedAlreadyStartedTest() {
        Mockito.when(scoreBoardRepository.findAllMatches())
                .thenReturn(List.of(new Match("HTeam", 1, "ATeam", 2)));

        TeamAlreadyPlayingException exception = Assertions.assertThrows(TeamAlreadyPlayingException.class
                , () -> this.scoreBoardService.startNewMatch("HTeam", "ATeam")
                , "Expected to throw Exception but it didn't!"
        );
        Assertions.assertEquals("This Match already started before!", exception.getMessage()
                , "returned message from exception doesn't match the expected value!");

    }

    @DisplayName("Start NewMatch Successfully Test")
    @Test
    void startNewMatchSuccessfullyTest() throws TeamAlreadyPlayingException {
        Mockito.when(scoreBoardRepository.findAllMatches())
                .thenReturn(List.of(new Match("HTeam", 1, "ATeam", 2)));
        Mockito.when(scoreBoardRepository.addMatch(ArgumentMatchers.any(Match.class)))
                .thenReturn(true);
        boolean started = scoreBoardService.startNewMatch("BTeam", "CTeam");
        Assertions.assertTrue(started, "Match didn't start successfully!");
    }

}
