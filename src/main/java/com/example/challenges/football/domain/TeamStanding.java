package com.example.challenges.football.domain;

import com.example.challenges.football.enumeration.Result;

public record TeamStanding(String teamName, int points, int goalDifference, int goalsScored) {

    public static int calculateTeamResult(int goalsFor, int goalsAgainst) {
        if (goalsFor > goalsAgainst) {
            return Result.WIN.getPoints();
        } else if (goalsFor == goalsAgainst) {
            return Result.DRAW.getPoints();
        }
        return Result.LOSE.getPoints();
    }

    public TeamStanding withMatchResult(int goalsFor, int goalsAgainst) {
        return new TeamStanding(teamName,
                points + calculateTeamResult(goalsFor, goalsAgainst),
                goalDifference + (goalsFor - goalsAgainst),
                goalsScored + goalsFor);
    }
}
