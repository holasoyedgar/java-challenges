package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record PrioritizedOrderCommand(String orderId,
                                      BigDecimal price,
                                      int quantity,
                                      int order) {
    public PrioritizedOrderCommand withSubtractedExecutedQuantity(int executedQuantity) {
        return new PrioritizedOrderCommand(orderId, price, quantity - executedQuantity, order);
    }
}
