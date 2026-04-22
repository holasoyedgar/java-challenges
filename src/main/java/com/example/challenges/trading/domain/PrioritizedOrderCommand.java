package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record PrioritizedOrderCommand(String orderId,
                                      BigDecimal price,
                                      int quantity,
                                      int order) {
    public static PrioritizedOrderCommand fromOrderCommand(OrderCommand command, int order) {
        return new PrioritizedOrderCommand(command.orderId(), command.price(), command.quantity(), order);
    }

    public PrioritizedOrderCommand withSubtractedExecutedQuantity(int executedQuantity) {
        return new PrioritizedOrderCommand(orderId, price, quantity - executedQuantity, order);
    }
}
