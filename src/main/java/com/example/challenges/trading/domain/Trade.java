package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record Trade(
        String makerOrderId,
        String takerOrderId,
        BigDecimal executionPrice,
        int executedQuantity
) {
    public static Trade fromPrioritizedOrderCommands(PrioritizedOrderCommand buyOrderCommand, PrioritizedOrderCommand sellOrderCommand) {
        int buyOrderCommandOrder = buyOrderCommand.order();
        int sellOrderCommandOrder = sellOrderCommand.order();
        String makerOrderId;
        String takerOrderId;
        BigDecimal executionPrice;
        if (buyOrderCommandOrder > sellOrderCommandOrder) {
            makerOrderId = sellOrderCommand.orderId();
            takerOrderId = buyOrderCommand.orderId();
            executionPrice = sellOrderCommand.price();
        } else {
            makerOrderId = buyOrderCommand.orderId();
            takerOrderId = sellOrderCommand.orderId();
            executionPrice = buyOrderCommand.price();
        }
        int executedQuantity = Math.min(buyOrderCommand.quantity(), sellOrderCommand.quantity());
        return new Trade(makerOrderId, takerOrderId, executionPrice, executedQuantity);
    }
}