package com.example.challenges.billing.domain;

import com.example.challenges.billing.enumeration.Status;

import java.math.BigDecimal;

public record Subscription(String userId, String planId, String status, BigDecimal monthlyRate, int monthsOverdue) {
    private static final String PENALTY_RATE = "0.15";

    public boolean isValid() {
        return userId != null &&
                planId != null &&
                Status.isValid(status) &&
                monthlyRate != null &&
                monthlyRate.compareTo(BigDecimal.ZERO) >= 0 &&
                monthsOverdue >= 0;
    }

    public boolean isCancelled() {
        return Status.CANCELLED.name().equals(status);
    }

    public boolean isPastDue() {
        return Status.PAST_DUE.name().equals(status);
    }

    public BigDecimal calculateMonthlyCharge() {
        BigDecimal charge = monthlyRate;
        if (isPastDue()) {
            BigDecimal penalty = charge.multiply(new BigDecimal(PENALTY_RATE))
                    .multiply(new BigDecimal(monthsOverdue));
            charge = charge.add(penalty);
        }

        return charge;
    }
}
