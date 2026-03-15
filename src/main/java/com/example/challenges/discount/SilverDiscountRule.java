package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class SilverDiscountRule implements DiscountRule {
    @Override
    public BigDecimal calculate(Order order) {
        return order.calculateTotalCost().multiply(new BigDecimal("0.05"));
    }

    @Override
    public boolean isApplicable(Order order) {
        return "SILVER".equals(order.customer().tier());
    }
}
