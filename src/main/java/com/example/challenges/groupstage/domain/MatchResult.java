package com.example.challenges.groupstage.domain;

public record MatchResult(
        String homeTeam,
        String awayTeam,
        int homeGoals,
        int awayGoals
) {}