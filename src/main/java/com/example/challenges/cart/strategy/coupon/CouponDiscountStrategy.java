package com.example.challenges.cart.strategy.coupon;

import com.example.challenges.cart.domain.CartTotals;

public interface CouponDiscountStrategy {
    CartTotals applyCoupon(CartTotals cartTotals);
}
