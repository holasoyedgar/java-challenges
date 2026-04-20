package com.example.challenges.groupstage.domain;

import com.example.challenges.groupstage.enumeration.Result;

public record TeamStanding(
        String teamName,
        int points,
        int goalDifference,
        int goalsScored
) {
    public static TeamStanding fromResult(String teamName, int goalsFor, int goalsAgainst) {
        return new TeamStanding(teamName,
                Result.fromGoals(goalsFor, goalsAgainst).getAwardedPoints(),
                goalsFor - goalsAgainst,
                goalsFor);
    }

    public TeamStanding merge(TeamStanding other) {
        return new TeamStanding(teamName,
                points + other.points,
                goalDifference + other.goalDifference,
                goalsScored + other.goalsScored);
    }
}