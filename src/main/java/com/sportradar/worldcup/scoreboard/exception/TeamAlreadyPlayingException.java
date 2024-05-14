package com.sportradar.worldcup.scoreboard.exception;

public class TeamAlreadyPlayingException extends Exception {


    public TeamAlreadyPlayingException(String message) {
        super(message);
    }

    public TeamAlreadyPlayingException(String message, Throwable cause) {
        super(message, cause);
    }
}
