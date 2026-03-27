package com.example.challenges.sofipo;

import com.example.challenges.sofipo.domain.InvestmentReceipt;
import com.example.challenges.sofipo.domain.InvestmentRequest;
import com.example.challenges.sofipo.enumeration.InvestmentTerm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SofipoYieldCalculator {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final BigDecimal BANKING_YEAR_DAYS = new BigDecimal("360");
    private static final BigDecimal TAX_EXEMPT_CAPITAL = new BigDecimal("200000");
    private static final BigDecimal TAX_RATE = new BigDecimal("0.005");

    public InvestmentReceipt calculateYield(InvestmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is null");
        }

        return InvestmentTerm.fromDays(request.termDays())
                .map(investmentTerm -> {
                    BigDecimal grossYield = calculateGrossYield(request, investmentTerm.getRate());
                    BigDecimal taxRetained = calculateTaxRetained(request);
                    BigDecimal netYield = grossYield.subtract(taxRetained);
                    return new InvestmentReceipt(
                            request.principal(),
                            grossYield.setScale(SCALE, ROUNDING_MODE),
                            taxRetained.setScale(SCALE, ROUNDING_MODE),
                            netYield.setScale(SCALE, ROUNDING_MODE)
                    );
                })
                .orElseGet(() -> new InvestmentReceipt(request.principal(),
                        BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE),
                        BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE),
                        BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE)));
    }


    private static BigDecimal calculateGrossYield(InvestmentRequest request, BigDecimal investmentTermRate) {
        return request.principal()
                .multiply(investmentTermRate)
                .multiply(BigDecimal.valueOf(request.termDays()))
                .divide(BANKING_YEAR_DAYS, SCALE, ROUNDING_MODE);
    }

    private static BigDecimal calculateTaxRetained(InvestmentRequest request) {
        return calculateTaxBase(request)
                .multiply(TAX_RATE)
                .multiply(BigDecimal.valueOf(request.termDays()))
                .divide(BANKING_YEAR_DAYS, SCALE, ROUNDING_MODE);
    }

    private static BigDecimal calculateTaxBase(InvestmentRequest request) {
        return BigDecimal.ZERO.max(request.principal().subtract(TAX_EXEMPT_CAPITAL));
    }
}