package com.example.challenges.cart;

import com.example.challenges.cart.domain.CartReceipt;
import com.example.challenges.cart.domain.CartRequest;
import com.example.challenges.cart.domain.CartTotals;
import com.example.challenges.cart.factory.CouponDiscountFactory;
import com.example.challenges.cart.factory.ItemDiscountFactory;
import com.example.challenges.cart.strategy.coupon.CouponDiscountStrategy;
import com.example.challenges.cart.strategy.item.ItemDiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartProcessor {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public CartReceipt processCart(CartRequest request) {
        if (request == null || request.items() == null) {
            return new CartReceipt(BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE), BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE));
        }

        CartTotals subtotals = request.items()
                .stream()
                .filter(item -> item != null && item.isValid())
                .map(item -> {
                    ItemDiscountStrategy itemDiscountStrategy = ItemDiscountFactory.getStrategy(item.type());
                    return new CartTotals(item.calculateItemTotal(), itemDiscountStrategy.calculateDiscount(item));
                })
                .reduce(new CartTotals(BigDecimal.ZERO, BigDecimal.ZERO), CartTotals::add);

        CouponDiscountStrategy couponDiscountStrategy = CouponDiscountFactory.getStrategy(request.couponCode());
        CartTotals finalTotals = couponDiscountStrategy.applyCoupon(subtotals);

        BigDecimal finalTotal = finalTotals.subtotal().subtract(finalTotals.totalDiscount());

        return new CartReceipt(finalTotal.setScale(SCALE, ROUNDING_MODE), finalTotals.totalDiscount().setScale(SCALE, ROUNDING_MODE));
    }
}