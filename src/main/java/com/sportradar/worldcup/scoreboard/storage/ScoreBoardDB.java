package com.sportradar.worldcup.scoreboard.storage;

import com.sportradar.worldcup.scoreboard.model.Match;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This ScoreBoardDB is our In memory storage, it's just a singleton List of @{@link Math},
 * It's an instance of @{@link CopyOnWriteArrayList} to be Thread safe
 **/
public class ScoreBoardDB {

    private final static List<Match> MATCH_LIST = new CopyOnWriteArrayList<>();

    private ScoreBoardDB() {
    }

    public static List<Match> getDB() {
        return MATCH_LIST;
    }
}
