package com.example.challenges.cart.strategy.item;

import com.example.challenges.cart.domain.CartItem;

import java.math.BigDecimal;

public class ClothingDiscountStrategy implements ItemDiscountStrategy {
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.15");

    @Override
    public BigDecimal calculateDiscount(CartItem cartItem) {
        return cartItem.calculateItemTotal().multiply(DISCOUNT_RATE);
    }
}
