package com.example.challenges.groupstage.domain;

import com.example.challenges.groupstage.enumeration.Result;

public record TeamStanding(
        String teamName,
        int points,
        int goalDifference,
        int goalsScored
) {
    public static TeamStanding defaultTeamStanding(String teamName) {
        return new TeamStanding(teamName, 0, 0, 0);
    }

    public TeamStanding withResult(int goalsFor, int goalsAgainst) {
        return new TeamStanding(teamName,
                points + calculateTeamResult(goalsFor, goalsAgainst).getAwardedPoints(),
                goalDifference + (goalsFor - goalsAgainst),
                goalsScored + goalsFor);
    }

    public Result calculateTeamResult(int goalsFor, int goalsAgainst) {
        if (goalsFor > goalsAgainst) {
            return Result.WIN;
        }
        if (goalsFor == goalsAgainst) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }
}