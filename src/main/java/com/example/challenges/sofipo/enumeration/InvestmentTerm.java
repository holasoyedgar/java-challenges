package com.example.challenges.sofipo.enumeration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public enum InvestmentTerm {
    SHORT_TERM(30, new BigDecimal("0.108")),
    MIDDLE_TERM(90, new BigDecimal("0.126")),
    LONG_TERM(360, new BigDecimal("0.150"));

    private final int days;
    private final BigDecimal rate;

    InvestmentTerm(int days, BigDecimal rate) {
        this.days = days;
        this.rate = rate;
    }

    public static Optional<InvestmentTerm> fromDays(int days) {
        return Arrays.stream(values()).filter(term -> term.days == days).findFirst();
    }

    public BigDecimal getRate() {
        return rate;
    }
}
