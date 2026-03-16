package com.example.challenges.savings;

import java.util.List;
import java.util.Objects;

public record ChallengeRequest(List<DailyDeposit> deposits) {
    public ChallengeRequest {
        deposits = deposits != null ? deposits.stream()
                .filter(Objects::nonNull)
                .filter(deposit -> deposit.amount() != null)
                .toList() : List.of();
    }
}
