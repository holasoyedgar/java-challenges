package com.example.challenges.football.domain;

import com.example.challenges.football.enumeration.Result;

public record TeamStanding(String teamName, int points, int goalDifference, int goalsScored) {

    public TeamStanding(String teamName, int goalsFor, int goalsAgainst) {
        this(teamName, calculateTeamResult(goalsFor, goalsAgainst), goalsFor - goalsAgainst, goalsFor);
    }

    public TeamStanding combineWith(TeamStanding other) {
        return new TeamStanding(teamName,
                points + other.points,
                goalDifference + other.goalDifference,
                goalsScored + other.goalsScored);
    }

    public static int calculateTeamResult(int goalsFor, int goalsAgainst) {
        if (goalsFor > goalsAgainst) {
            return Result.WIN.getPoints();
        } else if (goalsFor == goalsAgainst) {
            return Result.DRAW.getPoints();
        }
        return Result.LOSE.getPoints();
    }
}
