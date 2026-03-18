package com.example.challenges.football.domain;

import com.example.challenges.football.enumeration.Result;

import java.util.Map;

public record TeamStanding(String teamName, int points, int goalDifference, int goalsScored) {
    public static TeamStanding calculateTeamStanding(Map<String, TeamStanding> standings, String teamName, int goalsFor, int goalsAgainst) {
        TeamStanding teamStandings = standings.getOrDefault(teamName, new TeamStanding(teamName, 0, 0, 0));

        return new TeamStanding(teamStandings.teamName,
                teamStandings.points + calculateTeamResult(goalsFor, goalsAgainst),
                teamStandings.goalDifference + (goalsFor - goalsAgainst),
                teamStandings.goalsScored + goalsFor);
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
