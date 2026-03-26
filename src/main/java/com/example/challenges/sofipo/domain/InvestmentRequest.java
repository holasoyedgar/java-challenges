package com.example.challenges.sofipo.domain;

import java.math.BigDecimal;

import static com.example.challenges.sofipo.SofipoYieldCalculator.ROUNDING_MODE;

public record InvestmentRequest(
        BigDecimal principal,
        int termDays
) {
    private static final BigDecimal BANKING_YEAR_DAYS = new BigDecimal("360");
    private static final int CALCULATION_SCALE = 10;
    private static final BigDecimal TAX_EXEMPT_CAPITAL = new BigDecimal("200000");
    private static final BigDecimal TAX_RATE = new BigDecimal("0.005");


    public InvestmentRequest {
        if (principal == null || principal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Principal is invalid");
        }

        if (termDays <= 0) {
            throw new IllegalArgumentException("termDays is negative or zero.");
        }
    }

    public BigDecimal calculateGrossYield(BigDecimal investmentTermRate) {
        return principal
                .multiply(investmentTermRate.divide(BANKING_YEAR_DAYS, CALCULATION_SCALE, ROUNDING_MODE))
                .multiply(BigDecimal.valueOf(termDays));
    }

    public BigDecimal calculateTaxRetained() {
        return calculateTaxBase()
                .multiply(TAX_RATE.divide(BANKING_YEAR_DAYS, CALCULATION_SCALE, ROUNDING_MODE))
                .multiply(BigDecimal.valueOf(termDays));
    }

    public BigDecimal calculateTaxBase() {
        return BigDecimal.ZERO.max(principal.subtract(TAX_EXEMPT_CAPITAL));
    }
}