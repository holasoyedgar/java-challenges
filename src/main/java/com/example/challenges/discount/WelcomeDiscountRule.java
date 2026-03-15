package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class WelcomeDiscountRule implements DiscountRule {
    @Override
    public BigDecimal calculate(Order order) {
        return new BigDecimal("20.00");
    }

    @Override
    public boolean isApplicable(Order order) {
        return "WELCOME".equals(order.promoCode()) && order.totalCost().compareTo(new BigDecimal("50.00")) > 0;
    }
}
