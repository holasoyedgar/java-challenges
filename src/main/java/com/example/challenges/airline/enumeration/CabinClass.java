package com.example.challenges.airline.enumeration;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum CabinClass {
    ECONOMY(new BigDecimal("1")),
    BUSINESS(new BigDecimal("1.5")),
    FIRST(new BigDecimal("2"));

    private final BigDecimal rate;

    CabinClass(BigDecimal rate) {
        this.rate = rate;
    }

    public int calculateQualifyingMiles(int distance) {

        return BigDecimal.valueOf(distance)
                .multiply(rate)
                .setScale(0, RoundingMode.DOWN)
                .intValue();
    }
}
