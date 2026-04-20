package com.example.challenges.groupstage.domain;

public record TeamStanding(
        String teamName,
        int points,
        int goalDifference,
        int goalsScored
) {}