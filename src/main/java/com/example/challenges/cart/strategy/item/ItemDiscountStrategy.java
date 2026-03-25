package com.example.challenges.cart.strategy.item;

import com.example.challenges.cart.domain.CartItem;

import java.math.BigDecimal;

public interface ItemDiscountStrategy {
    BigDecimal calculateDiscount(CartItem cartItem);
}
