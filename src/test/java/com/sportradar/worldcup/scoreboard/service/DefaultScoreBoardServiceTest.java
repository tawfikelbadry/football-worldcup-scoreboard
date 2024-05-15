package com.sportradar.worldcup.scoreboard.service;

import com.sportradar.worldcup.scoreboard.exception.MatchNotExistException;
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

    @Test
    @DisplayName("Update MatchScore Successfully Test")
    void updateMatchScoreSuccessfullyTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 0, "ATeam", 2);
        Mockito.when(scoreBoardRepository.exists(match)).thenReturn(true);
        Mockito.when(scoreBoardRepository.updateMatch(match)).thenReturn(true);

        boolean updated = scoreBoardService.updateMatchScore(match);
        Assertions.assertTrue(updated, "Failed to update Match!");
    }

    @Test
    @DisplayName("Update MatchScore Failed MatchNotExist Test")
    void updateMatchScoreFailedMatchNotExistTest() {
        Match match = new Match("HTeam", 0, "ATeam", 2);
        Mockito.when(scoreBoardRepository.exists(match)).thenReturn(false);

        MatchNotExistException exception = Assertions.assertThrows(
                MatchNotExistException.class, () -> scoreBoardService.updateMatchScore(match),
                "Expected to throw Exception but it didn't!"
        );
        Assertions.assertEquals("Match is not exist!", exception.getMessage());
    }

    @Test
    @DisplayName("Update MatchScore Failed EmptyValues Test")
    void updateMatchScoreFailedMatchNotEmptyValuesTest() throws MatchNotExistException {
        Match match = new Match(null, 0, "", 2);
        boolean updated = scoreBoardService.updateMatchScore(match);
        Assertions.assertFalse(updated, "Expected false but returned true");
        match = new Match("ATeam", 0, "", 2);
        updated = scoreBoardService.updateMatchScore(match);
        Assertions.assertFalse(updated, "Expected false but returned true");
        updated = scoreBoardService.updateMatchScore(null);
        Assertions.assertFalse(updated, "Expected false but returned true");
    }

    @Test
    void finishMatchSuccessfullyTest() throws MatchNotExistException {
        Match match = new Match("HTeam", 0, "ATeam", 2);
        Mockito.when(scoreBoardRepository.exists(match)).thenReturn(true);
        Mockito.when(scoreBoardRepository.removeMatch(match)).thenReturn(true);

        boolean finished = scoreBoardService.finishMatch(match);
        Assertions.assertTrue(finished, "Failed to finish Match");
    }

    @Test
    void finishMatchFailedMatchNotExistTest() {
        Match match = new Match("HTeam", 0, "ATeam", 2);
        Mockito.when(scoreBoardRepository.exists(match)).thenReturn(false);

        Exception exception = Assertions.assertThrows(MatchNotExistException.class, () -> scoreBoardService.finishMatch(match));
        Assertions.assertEquals("Match is not exist!", exception.getMessage(), "Exception message is not correct!");
    }

    @Test
    void finishMatchFailedMatchEmptyValuesTest() throws MatchNotExistException {
        Match match = new Match("", 0, "ATeam", 2);
        boolean finished = this.scoreBoardService.finishMatch(match);
        Assertions.assertFalse(finished, "Expected false but returned true");

        match = new Match("AJTeam", 0, null, 2);
        finished = this.scoreBoardService.finishMatch(match);
        Assertions.assertFalse(finished, "Expected false but returned true");
    }

    @Test
    @DisplayName("Get Ordered Summary Test")
    void getOrderedSummaryTest() {
        List<Match> matchListFromDB = List.of(
                new Match("Mexico", 0, "Canada", 5),
                new Match("Spain", 10, "Brazil", 2),
                new Match("Germany", 2, "France", 2),
                new Match("Uruguay", 6, "Italy", 6),
                new Match("Argentina", 3, "Australia", 1)
        );
        Mockito.when(this.scoreBoardRepository.findAllMatches()).thenReturn(matchListFromDB);
        List<Match> summary = this.scoreBoardService.getSummary();

        List<Match> sortedMatchesList = List.of(
                new Match("Uruguay", 6, "Italy", 6),
                new Match("Spain", 10, "Brazil", 2),
                new Match("Mexico", 0, "Canada", 5),
                new Match("Argentina", 3, "Australia", 1),
                new Match("Germany", 2, "France", 2)
        );

        for (int i = 0; i < summary.size(); i++) {
            Assertions.assertTrue(isSameMatch(summary.get(i), sortedMatchesList.get(i)), "the summary returns the matches in wrong order!");
        }
    }

    private boolean isSameMatch(Match match, Match matchToUpdate) {
        return match.getHomeTeam().getName().equals(matchToUpdate.getHomeTeam().getName()) &&
                match.getAwayTeam().getName().equals(matchToUpdate.getAwayTeam().getName());
    }
}
