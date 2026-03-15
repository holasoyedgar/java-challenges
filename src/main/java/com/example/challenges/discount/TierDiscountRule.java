package com.example.challenges.discount;

import com.example.challenges.discount.domain.Customer;
import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;
import java.util.Optional;

public class TierDiscountRule implements DiscountRule {
    private final String tier;
    private final BigDecimal discountRate;

    public TierDiscountRule(String tier, String discountRate) {
        this.tier = tier;
        this.discountRate = new BigDecimal(discountRate);
    }


    @Override
    public BigDecimal calculate(Order order) {
        return order.calculateTotalCost().multiply(discountRate);
    }

    @Override
    public boolean isApplicable(Order order) {
        return Optional.ofNullable(order.customer())
                .map(Customer::tier)
                .filter(tier::equals)
                .isPresent();
    }
}
