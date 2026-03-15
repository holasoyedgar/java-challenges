package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public interface DiscountRule {
    BigDecimal calculate(Order order);
    boolean isApplicable(Order order);
}
