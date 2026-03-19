package com.example.challenges.finance.strategy;

import com.example.challenges.finance.domain.StockTrade;

import java.math.BigDecimal;

public class ExemptTaxRule implements TaxRule {
    @Override
    public BigDecimal calculate(StockTrade trade) {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean isApplicable(StockTrade trade) {
        return trade.calculateProfit().compareTo(BigDecimal.ZERO) <= 0;
    }
}
