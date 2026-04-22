package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record OrderCommand(
        String orderId,
        OrderSide side,
        BigDecimal price,
        int quantity
) {
    public OrderCommand {
        if (orderId == null) {
            throw new IllegalArgumentException("The order id must not be null.");
        }

        if (side == null) {
            throw new IllegalArgumentException("The order side was not specified.");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The price must be greater than zero.");
        }

        if (quantity < 1) {
            throw new IllegalArgumentException("The quantity must be greater than 0.");
        }
    }
}