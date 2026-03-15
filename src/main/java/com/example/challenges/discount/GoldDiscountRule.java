package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class GoldDiscountRule implements DiscountRule {
    @Override
    public BigDecimal calculate(Order order) {
        return order.totalCost().multiply(new BigDecimal("0.10"));
    }

    @Override
    public boolean isApplicable(Order order) {
        return "GOLD".equals(order.customer().tier());
    }
}
