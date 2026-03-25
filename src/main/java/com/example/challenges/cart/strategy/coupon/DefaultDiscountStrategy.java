package com.example.challenges.cart.strategy.coupon;

import com.example.challenges.cart.domain.CartTotals;

public class DefaultDiscountStrategy implements CouponDiscountStrategy {
    @Override
    public CartTotals applyCoupon(CartTotals cartTotals) {
        return cartTotals;
    }
}
