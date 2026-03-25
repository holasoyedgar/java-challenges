package com.example.challenges.cart.domain;

import java.math.BigDecimal;

public record CartItem(String name, BigDecimal price, Integer quantity, String type) {
    public BigDecimal calculateItemTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public boolean isValid() {
        return name != null && price != null &&
                price.compareTo(BigDecimal.ZERO) > 0 &&
                quantity != null &&
                quantity > 0 &&
                type != null;
    }
}