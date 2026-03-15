package com.example.challenges.discount;

import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class LegacyOrderDiscountCalculator {

    private final int SCALE = 2;
    private final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private final List<DiscountRule> discountRules;

    public LegacyOrderDiscountCalculator(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    public BigDecimal calculateDiscount(Order order) {
        if (order == null || !order.isValidOrder()) {
            return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
        }

        return discountRules
                .stream().filter(discountRule -> discountRule.isApplicable(order))
                .findFirst()
                .map(discountRule -> discountRule.calculate(order))
                .orElse(BigDecimal.ZERO)
                .setScale(SCALE, ROUNDING_MODE);
    }
}