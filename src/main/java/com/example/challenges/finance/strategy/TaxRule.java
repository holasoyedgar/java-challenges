package com.example.challenges.finance.strategy;

import com.example.challenges.finance.domain.StockTrade;

import java.math.BigDecimal;

public interface TaxRule {
    BigDecimal calculate(StockTrade trade);
    boolean isApplicable(StockTrade trade);
}
