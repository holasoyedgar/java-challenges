package com.example.challenges.savings;

import java.math.BigDecimal;

public record DailyDeposit(int dayOfYear, BigDecimal amount) {
    public DailyDeposit {
        if (dayOfYear <= 0) {
            throw new IllegalArgumentException("The day of the year cannot be zero or negative");
        }
        if (dayOfYear > 366) {
            throw new IllegalArgumentException("The number is greater than the number of days in a year");
        }
        if (amount != null && amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The amount cannot be less than 0");
        }
    }

    public boolean isSuccessfulDay() {
        return amount.compareTo(BigDecimal.valueOf(dayOfYear)) >= 0;
    }

    public boolean isDayConsecutive(int lastDay) {
        return lastDay == 0 || lastDay == dayOfYear - 1;
    }
}
