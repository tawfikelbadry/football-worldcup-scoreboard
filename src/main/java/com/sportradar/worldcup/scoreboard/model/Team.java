package com.sportradar.worldcup.scoreboard.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Team {

    private String name;
    private int score;

}
