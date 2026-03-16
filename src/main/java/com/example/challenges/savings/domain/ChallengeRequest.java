package com.example.challenges.savings.domain;

import java.util.List;
import java.util.Objects;

public record ChallengeRequest(List<DailyDeposit> deposits) {
    public ChallengeRequest {
        if (deposits == null) {
            throw new IllegalArgumentException("deposits is a mandatory field, it cannot be null");
        }
        if (deposits.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("The request contains null deposits");
        }
        deposits = List.copyOf(deposits);
    }
}
