package com.example.challenges.cart.strategy.item;

import com.example.challenges.cart.domain.CartItem;

import java.math.BigDecimal;

public class ElectronicsDiscountStrategy implements ItemDiscountStrategy {
    private static final BigDecimal ELECTRONICS_PRICE_DISCOUNT = new BigDecimal("1000");
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.05");

    @Override
    public BigDecimal calculateDiscount(CartItem cartItem) {
        if (cartItem.price().compareTo(ELECTRONICS_PRICE_DISCOUNT) > 0) {
            return cartItem.calculateItemTotal().multiply(DISCOUNT_RATE);
        }

        return BigDecimal.ZERO;
    }
}
