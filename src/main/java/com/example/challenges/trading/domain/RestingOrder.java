package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record RestingOrder(
        String orderId,
        BigDecimal price,
        int remainingQuantity
) {
    public static RestingOrder fromPrioritizedOrderCommand(PrioritizedOrderCommand orderCommand) {
        return new RestingOrder(orderCommand.orderId(), orderCommand.price(), orderCommand.quantity());
    }
}