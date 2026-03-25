package com.example.challenges.cart.strategy.item;

import com.example.challenges.cart.domain.CartItem;

import java.math.BigDecimal;

public class DefaultItemDiscountStrategy implements ItemDiscountStrategy {
    @Override
    public BigDecimal calculateDiscount(CartItem cartItem) {
        return BigDecimal.ZERO;
    }
}
