package com.sportradar.worldcup.scoreboard.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Match {

    private Team homeTeam;
    private Team awayTeam;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = Team.builder().name(homeTeam).score(0).build();
        this.awayTeam = Team.builder().name(awayTeam).score(0).build();
    }

    public Match(String homeTeam, int homeTeamScore, String awayTeam, int awayTeamScore) {
        this.homeTeam = Team.builder().name(homeTeam).score(homeTeamScore).build();
        this.awayTeam = Team.builder().name(awayTeam).score(awayTeamScore).build();
    }

    public boolean hasTeam(String team) {
        return this.homeTeam.getName().equals(team) || this.awayTeam.getName().equals(team);
    }
}