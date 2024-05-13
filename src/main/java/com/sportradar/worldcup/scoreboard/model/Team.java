package com.sportradar.worldcup.scoreboard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class Team {

    private String name;
    private int score;

}
