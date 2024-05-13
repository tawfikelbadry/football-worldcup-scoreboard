package com.sportradar.worldcup.scoreboard.repository;

import com.sportradar.worldcup.scoreboard.model.Match;
import com.sportradar.worldcup.scoreboard.repository.impl.DefaultScoreBoardRepository;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DefaultScoreBoardRepository Tests")
public class DefaultScoreBoardRepositoryTest {

    private final ScoreBoardRepository scoreBoardRepository = new DefaultScoreBoardRepository();

    @BeforeEach
    void addTestData() {
        Match match1 = new Match("XTeam", "YTeam");
        Match match2 = new Match("MTeam", "NTeam");
        this.scoreBoardRepository.addMatch(match1);
        this.scoreBoardRepository.addMatch(match2);
    }

    @Test
    @DisplayName("AddMatch successfully Test")
    void addMatchSuccessfullyTest() {
        Match match = new Match("RTeam", "LTeam");
        boolean added = this.scoreBoardRepository.addMatch(match);
        assertTrue(added, "Failed to add Match!");
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(3, matchList.size(), "the number of saved matches is wrong!");
        assertEquals("RTeam", matchList.get(2).getHomeTeam().getName());
        assertEquals("LTeam", matchList.get(2).getAwayTeam().getName());
        assertEquals(0, matchList.get(2).getHomeTeam().getScore());
        assertEquals(0, matchList.get(2).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("AddMatch failed Test")
    void addMatchFailedTest() {
        Match match = new Match("XTeam", "YTeam");
        boolean added=this.scoreBoardRepository.addMatch(match);
        assertFalse(added, "added Match however the match existed already!");

        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(2, matchList.size(), "the number of saved matches is wrong!");
        assertEquals("XTeam", matchList.get(0).getHomeTeam().getName());
        assertEquals("YTeam", matchList.get(0).getAwayTeam().getName());
        assertEquals(0, matchList.get(0).getHomeTeam().getScore());
        assertEquals(0, matchList.get(0).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("UpdateMatch successfully Test")
    void updateMatchSuccessfullyTest() {

        boolean updated = this.scoreBoardRepository.updateMatch(new Match("MTeam", 1, "NTeam", 2));
        assertTrue(updated, "Failed to update the Match");
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(2, matchList.size(), "the number of saved matches is wrong!");
        assertEquals("MTeam", matchList.get(1).getHomeTeam().getName());
        assertEquals("NTeam", matchList.get(1).getAwayTeam().getName());
        assertEquals(1, matchList.get(1).getHomeTeam().getScore());
        assertEquals(2, matchList.get(1).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("UpdateMatch failed Test")
    void updateMatchFailedTest() {
        boolean updated = this.scoreBoardRepository.updateMatch(new Match("MTeam", 1, "YTeam", 2));
        assertFalse(updated, "The match doesn't exist but it returns true!");
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(2, matchList.size(), "the number of saved matches is wrong!");
        assertEquals("MTeam", matchList.get(1).getHomeTeam().getName());
        assertEquals("NTeam", matchList.get(1).getAwayTeam().getName());
        assertEquals(0, matchList.get(1).getHomeTeam().getScore());
        assertEquals(0, matchList.get(1).getAwayTeam().getScore());
    }

    @Test
    @DisplayName("RemoveMatch successfully Test")
    void removeMatchSuccessfullyTest() {
        boolean removed = this.scoreBoardRepository.removeMatch(new Match("XTeam", "YTeam"));
        assertTrue(removed, "Can't find Match, failed to remove it");
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(1, matchList.size(), "the number of returned matches is wrong!");
        assertEquals("MTeam", matchList.get(0).getHomeTeam().getName());
        assertEquals("NTeam", matchList.get(0).getAwayTeam().getName());
    }

    @Test
    @DisplayName("RemoveMatch failed Test")
    void removeMatchFailedTest() {
        boolean removed = this.scoreBoardRepository.removeMatch(new Match("NotFoundTeam", "QTeam"));
        assertFalse(removed, "Match not exist, But it returned true");
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(2, matchList.size(), "the number of returned matches is wrong!");
        assertEquals("XTeam", matchList.get(0).getHomeTeam().getName());
        assertEquals("YTeam", matchList.get(0).getAwayTeam().getName());
    }

    @Test
    @DisplayName("FindAllMatches Test")
    void findAllMatchesTest() {
        List<Match> matchList = scoreBoardRepository.findAllMatches();
        assertEquals(2, matchList.size(), "the number of saved matches is wrong!");
        assertEquals("MTeam", matchList.get(1).getHomeTeam().getName());
        assertEquals("NTeam", matchList.get(1).getAwayTeam().getName());
        assertEquals(0, matchList.get(1).getHomeTeam().getScore());
        assertEquals(0, matchList.get(1).getAwayTeam().getScore());
    }

    @AfterEach
    void clearAll() {
        this.scoreBoardRepository.clearAll();
    }

}
