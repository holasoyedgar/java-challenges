package com.example.challenges.finance.strategy;

import com.example.challenges.finance.domain.StockTrade;
import com.example.challenges.finance.enumeration.HoldingPeriod;

import java.math.BigDecimal;

public class HoldingPeriodTaxRule implements TaxRule {
    private final HoldingPeriod holdingPeriod;
    private final BigDecimal taxRate;

    public HoldingPeriodTaxRule(HoldingPeriod holdingPeriod, BigDecimal taxRate) {
        this.holdingPeriod = holdingPeriod;
        this.taxRate = taxRate;
    }
    @Override
    public BigDecimal calculate(StockTrade trade) {
        return trade.calculateProfit().multiply(taxRate);
    }

    @Override
    public boolean isApplicable(StockTrade trade) {
        return trade.holdingPeriod().equals(holdingPeriod) &&
                trade.calculateProfit().compareTo(BigDecimal.ZERO) > 0;
    }
}
