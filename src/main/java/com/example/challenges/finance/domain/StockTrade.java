package com.example.challenges.finance.domain;

import com.example.challenges.finance.enumeration.HoldingPeriod;

import java.math.BigDecimal;

public record StockTrade(String ticker, BigDecimal buyPrice, BigDecimal sellPrice, Integer quantity, HoldingPeriod holdingPeriod) {
    public boolean isValid() {
        return ticker != null &&
                buyPrice != null &&
                buyPrice.compareTo(BigDecimal.ZERO) > 0 &&
                sellPrice != null &&
                sellPrice.compareTo(BigDecimal.ZERO) > 0 &&
                quantity != null &&
                quantity > 0 &&
                holdingPeriod != null;
    }

    public BigDecimal calculateProfit() {
        return sellPrice.subtract(buyPrice)
                .multiply(BigDecimal.valueOf(quantity));
    }
}
