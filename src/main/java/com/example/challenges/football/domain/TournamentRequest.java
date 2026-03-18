package com.example.challenges.football.domain;

import java.util.List;
import java.util.Objects;

public record TournamentRequest(List<MatchResult> matches) {
    public TournamentRequest {
        if (matches == null) {
            throw new IllegalArgumentException("matches are null.");
        }

        if (matches.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("matches contains null values.");
        }

        matches = List.copyOf(matches);
    }
}
