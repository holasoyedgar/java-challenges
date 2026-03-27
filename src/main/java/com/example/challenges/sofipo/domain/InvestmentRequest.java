package com.example.challenges.sofipo.domain;

import java.math.BigDecimal;

public record InvestmentRequest(
        BigDecimal principal,
        int termDays
) {
    public InvestmentRequest {
        if (principal == null || principal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Principal is invalid");
        }

        if (termDays <= 0) {
            throw new IllegalArgumentException("termDays is negative or zero.");
        }
    }
}