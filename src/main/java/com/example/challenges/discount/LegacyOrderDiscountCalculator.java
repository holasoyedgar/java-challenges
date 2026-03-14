package com.example.challenges.discount;

import com.example.challenges.discount.domain.Item;
import com.example.challenges.discount.domain.Order;

import java.math.BigDecimal;

public class LegacyOrderDiscountCalculator {

    public BigDecimal calculateDiscount(Order order) {
        if (order == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = BigDecimal.ZERO;
        if (order.items() != null) {
            for (Item item : order.items()) {
                if (item != null && item.price() != null) {
                    total = total.add(item.price().multiply(BigDecimal.valueOf(item.quantity())));
                }
            }
        }

        BigDecimal discount = BigDecimal.ZERO;
        if (order.customer() != null && order.customer().tier() != null) {
            if (order.customer().tier().equals("GOLD")) {
                discount = total.multiply(new BigDecimal("0.10"));
            } else if (order.customer().tier().equals("SILVER")) {
                discount = total.multiply(new BigDecimal("0.05"));
            }
        }

        if (discount.compareTo(BigDecimal.ZERO) == 0 && order.promoCode() != null) {
            if (order.promoCode().equals("WELCOME") && total.compareTo(new BigDecimal("50.00")) > 0) {
                discount = new BigDecimal("20.00");
            }
        }

        return discount;
    }
}