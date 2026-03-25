package com.example.challenges.cart.strategy.item;

import com.example.challenges.cart.domain.CartItem;

import java.math.BigDecimal;

public class GroceriesDiscountStrategy implements ItemDiscountStrategy {
    public static final int GROCERIES_QUANTITY_DISCOUNT = 5;
    public static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");

    @Override
    public BigDecimal calculateDiscount(CartItem cartItem) {
        if (cartItem.quantity() > GROCERIES_QUANTITY_DISCOUNT) {
            return cartItem.calculateItemTotal().multiply(DISCOUNT_RATE);
        }

        return BigDecimal.ZERO;
    }
}
