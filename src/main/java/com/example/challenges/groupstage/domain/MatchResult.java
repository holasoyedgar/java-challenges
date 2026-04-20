package com.example.challenges.groupstage.domain;

public record MatchResult(
        String homeTeam,
        String awayTeam,
        int homeGoals,
        int awayGoals
) {
    public MatchResult {
        if (homeTeam == null || awayTeam == null || homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Some team names are invalid.");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("A team cannot face itself");
        }

        if (homeGoals < 0 || awayGoals < 0) {
            throw new IllegalArgumentException("Goals must be a positive integer");
        }
    }
}