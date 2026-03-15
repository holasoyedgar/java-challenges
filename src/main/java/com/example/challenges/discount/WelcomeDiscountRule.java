package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class WelcomeDiscountRule implements DiscountRule {
    private static final BigDecimal DEFAULT_DISCOUNT = new BigDecimal("20.00");
    private static final String WELCOME_PROMO_CODE = "WELCOME";
    private static final BigDecimal MINIMUM_TOTAL_COST = new BigDecimal("50.00");

    @Override
    public BigDecimal calculate(Order order) {
        return DEFAULT_DISCOUNT;
    }

    @Override
    public boolean isApplicable(Order order) {
        return WELCOME_PROMO_CODE.equals(order.promoCode()) &&
                order.calculateTotalCost().compareTo(MINIMUM_TOTAL_COST) > 0;
    }
}
