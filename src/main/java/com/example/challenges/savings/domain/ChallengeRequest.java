package com.example.challenges.savings.domain;

import java.util.List;

public record ChallengeRequest(List<DailyDeposit> deposits) {
    public ChallengeRequest {
        if (deposits == null) {
            throw new IllegalArgumentException("deposits is a mandatory field, it cannot be null");
        }
        deposits = List.copyOf(deposits);
    }
}
