package com.example.challenges.sofipo;

import com.example.challenges.sofipo.domain.InvestmentReceipt;
import com.example.challenges.sofipo.domain.InvestmentRequest;
import com.example.challenges.sofipo.enumeration.InvestmentTerm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class SofipoYieldCalculator {

    private static final int RESULT_SCALE = 2;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public InvestmentReceipt calculateYield(InvestmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request is null");
        }
        Optional<InvestmentTerm> investmentTermOptional = InvestmentTerm.fromDays(request.termDays());
        if (investmentTermOptional.isEmpty()) {
            return new InvestmentReceipt(request.principal(),
                    BigDecimal.ZERO.setScale(RESULT_SCALE, ROUNDING_MODE),
                    BigDecimal.ZERO.setScale(RESULT_SCALE, ROUNDING_MODE),
                    BigDecimal.ZERO.setScale(RESULT_SCALE, ROUNDING_MODE));
        }
        BigDecimal grossYield = request.calculateGrossYield(investmentTermOptional.get().getRate());

        BigDecimal taxRetained = request.calculateTaxRetained();

        BigDecimal netYield = grossYield.subtract(taxRetained);

        return new InvestmentReceipt(
                request.principal(),
                grossYield.setScale(RESULT_SCALE, ROUNDING_MODE),
                taxRetained.setScale(RESULT_SCALE, ROUNDING_MODE),
                netYield.setScale(RESULT_SCALE, ROUNDING_MODE)
        );
    }
}