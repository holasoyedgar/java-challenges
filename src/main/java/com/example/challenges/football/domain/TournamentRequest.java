package com.example.challenges.football.domain;

import java.util.List;

public record TournamentRequest(List<MatchResult> matches) {
    public boolean isValid() {
        return matches != null;
    }

    public boolean areMatchesEmpty() {
        return matches.isEmpty();
    }
}
