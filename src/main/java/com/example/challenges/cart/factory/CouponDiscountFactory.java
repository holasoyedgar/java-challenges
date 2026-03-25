package com.example.challenges.cart.factory;

import com.example.challenges.cart.strategy.coupon.CouponDiscountStrategy;
import com.example.challenges.cart.strategy.coupon.DefaultDiscountStrategy;
import com.example.challenges.cart.strategy.coupon.Save20DiscountStrategy;

import java.util.Map;

public class CouponDiscountFactory {
    private static final Map<String, CouponDiscountStrategy> STRATEGIES = Map.of(
            "SAVE20", new Save20DiscountStrategy()
    );

    public static CouponDiscountStrategy getStrategy(String couponCode) {
        if (couponCode == null) {
            return new DefaultDiscountStrategy();
        }

        return STRATEGIES.getOrDefault(couponCode.toUpperCase(), new DefaultDiscountStrategy());
    }
}
