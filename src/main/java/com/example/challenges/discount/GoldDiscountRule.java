package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class GoldDiscountRule implements DiscountRule {
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");
    private static final String GOLD_TIER = "GOLD";

    @Override
    public BigDecimal calculate(Order order) {
        return order.calculateTotalCost().multiply(DISCOUNT_RATE);
    }

    @Override
    public boolean isApplicable(Order order) {
        return GOLD_TIER.equals(order.customer().tier());
    }
}
