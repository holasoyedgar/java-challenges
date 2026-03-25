package com.example.challenges.cart.strategy.coupon;

import com.example.challenges.cart.domain.CartTotals;

import java.math.BigDecimal;

public class Save20DiscountStrategy implements CouponDiscountStrategy {
    private static final BigDecimal DISCOUNT_AMOUNT = new BigDecimal("20");

    @Override
    public CartTotals applyCoupon(CartTotals cartTotals) {
        BigDecimal currentFinalTotal = cartTotals.subtotal().subtract(cartTotals.totalDiscount());

        BigDecimal discountAmount = DISCOUNT_AMOUNT;
        if (currentFinalTotal.compareTo(discountAmount) >= 0) {
            return new CartTotals(cartTotals.subtotal(), cartTotals.totalDiscount().add(discountAmount));
        } else {
            return new CartTotals(cartTotals.subtotal(), cartTotals.totalDiscount().add(currentFinalTotal));
        }
    }
}
