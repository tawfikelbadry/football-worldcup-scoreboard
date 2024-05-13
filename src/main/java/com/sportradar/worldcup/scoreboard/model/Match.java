package com.sportradar.worldcup.scoreboard.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Match {

    private Team homeTeam;
    private Team awayTeam;

}