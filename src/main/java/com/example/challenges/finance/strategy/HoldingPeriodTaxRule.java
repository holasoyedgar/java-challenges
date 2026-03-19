package com.example.challenges.finance.strategy;

import com.example.challenges.finance.domain.StockTrade;

import java.math.BigDecimal;

public class HoldingPeriodTaxRule implements TaxRule {
    private final String holdingPeriod;
    private final BigDecimal taxRate;

    public HoldingPeriodTaxRule(String holdingPeriod, BigDecimal taxRate) {
        this.holdingPeriod = holdingPeriod;
        this.taxRate = taxRate;
    }
    @Override
    public BigDecimal calculate(StockTrade trade) {
        return trade.calculateProfit().multiply(taxRate);
    }

    @Override
    public boolean isApplicable(StockTrade trade) {
        return trade.holdingPeriod().equals(holdingPeriod);
    }
}
