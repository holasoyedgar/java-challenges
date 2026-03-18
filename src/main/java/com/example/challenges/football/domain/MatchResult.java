package com.example.challenges.football.domain;

public record MatchResult(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
    public MatchResult {
        if (homeTeam == null || awayTeam == null) {
            throw new IllegalArgumentException("Invalid match, one or both teams are null.");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Invalid match, it must face two different teams.");
        }
        if (homeGoals < 0 || awayGoals < 0) {
            throw new IllegalArgumentException("Invalid match, goals are invalid.");
        }
    }

}
