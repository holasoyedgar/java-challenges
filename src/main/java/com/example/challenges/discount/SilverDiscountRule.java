package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class SilverDiscountRule implements DiscountRule {
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.05");
    private static final String SILVER_TIER = "SILVER";

    @Override
    public BigDecimal calculate(Order order) {
        return order.calculateTotalCost().multiply(DISCOUNT_RATE);
    }

    @Override
    public boolean isApplicable(Order order) {
        return SILVER_TIER.equals(order.customer().tier());
    }
}
