package com.example.challenges.cart;

import com.example.challenges.cart.domain.CartItem;
import com.example.challenges.cart.domain.CartReceipt;
import com.example.challenges.cart.domain.CartRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartProcessor {

    public CartReceipt processCart(CartRequest request) {
        if (request == null || request.items() == null) {
            return new CartReceipt(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (int i = 0; i < request.items().size(); i++) {
            CartItem item = request.items().get(i);

            if (item == null || item.price() == null || item.price().compareTo(BigDecimal.ZERO) <= 0 || item.quantity() == null || item.quantity() <= 0 || item.type() == null) {
                continue;
            }

            BigDecimal itemTotal = item.price().multiply(BigDecimal.valueOf(item.quantity()));
            BigDecimal itemDiscount = BigDecimal.ZERO;

            if (item.type().equals("ELECTRONICS")) {
                if (item.price().compareTo(new BigDecimal("1000")) > 0) {
                    itemDiscount = itemTotal.multiply(new BigDecimal("0.05"));
                }
            } else if (item.type().equals("GROCERIES")) {
                if (item.quantity() > 5) {
                    itemDiscount = itemTotal.multiply(new BigDecimal("0.10"));
                }
            } else if (item.type().equals("CLOTHING")) {
                itemDiscount = itemTotal.multiply(new BigDecimal("0.15"));
            }

            subtotal = subtotal.add(itemTotal);
            totalDiscount = totalDiscount.add(itemDiscount);
        }

        BigDecimal finalTotal = subtotal.subtract(totalDiscount);

        if (request.couponCode() != null && request.couponCode().equals("SAVE20")) {
            BigDecimal discountAmount = new BigDecimal("20");
            if (finalTotal.compareTo(discountAmount) >= 0) {
                finalTotal = finalTotal.subtract(discountAmount);
                totalDiscount = totalDiscount.add(discountAmount);
            } else {
                totalDiscount = totalDiscount.add(finalTotal);
                finalTotal = BigDecimal.ZERO;
            }
        }

        return new CartReceipt(finalTotal.setScale(2, RoundingMode.HALF_UP), totalDiscount.setScale(2, RoundingMode.HALF_UP));
    }
}