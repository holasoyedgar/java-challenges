package com.example.challenges.trading;

import com.example.challenges.trading.domain.*;

import java.math.BigDecimal;
import java.util.*;

public class LimitOrderBookEngine {

    public MatchingResult processOrders(OrderBookRequest request) {
        if (request == null || request.commands() == null) {
            return new MatchingResult(List.of(), new OrderBookSnapshot(List.of(), List.of()));
        }
        Queue<OrderCommandPrioritized> buyOrderCommands = new PriorityQueue<>(Comparator.comparing(OrderCommandPrioritized::price)
                .reversed()
                .thenComparing(OrderCommandPrioritized::orderId));

        Queue<OrderCommandPrioritized> sellOrderCommands = new PriorityQueue<>(Comparator.comparing(OrderCommandPrioritized::price)
                .thenComparing(OrderCommandPrioritized::orderId));

        for (int i = 0; i < request.commands().size(); i++) {
            OrderCommand orderCommand = request.commands().get(i);
            OrderCommandPrioritized orderCommandPrioritized = new OrderCommandPrioritized(orderCommand.orderId(), orderCommand.price(), orderCommand.quantity(), i);
            if (orderCommand.side().equals(OrderSide.BUY)) {
                buyOrderCommands.offer(orderCommandPrioritized);
            } else {
                sellOrderCommands.offer(orderCommandPrioritized);
            }
        }

        List<Trade> trades = new ArrayList<>();
        while (!buyOrderCommands.isEmpty() && !sellOrderCommands.isEmpty()) {
            OrderCommandPrioritized buyOrderCommand = buyOrderCommands.peek();
            OrderCommandPrioritized sellOrderCommand = sellOrderCommands.peek();
            if (buyOrderCommand.price().compareTo(sellOrderCommand.price()) < 0) {
                break;
            }
            Trade trade = getTrade(buyOrderCommand, sellOrderCommand);
            trades.add(trade);
            buyOrderCommands.poll();
            if (buyOrderCommand.quantity() > trade.executedQuantity()) {
                buyOrderCommands.offer(new OrderCommandPrioritized(buyOrderCommand.orderId(),
                        buyOrderCommand.price(),
                        buyOrderCommand.quantity() - trade.executedQuantity(),
                        buyOrderCommand.order()));
            }
            sellOrderCommands.poll();
            if (sellOrderCommand.quantity() > trade.executedQuantity()) {
                sellOrderCommands.offer(new OrderCommandPrioritized(sellOrderCommand.orderId(),
                        sellOrderCommand.price(),
                        sellOrderCommand.quantity() - trade.executedQuantity(),
                        sellOrderCommand.order()));
            }
        }

        List<RestingOrder> bids = new ArrayList<>();
        while (!buyOrderCommands.isEmpty()) {
            OrderCommandPrioritized buyOrderCommand = buyOrderCommands.peek();
            bids.add(new RestingOrder(buyOrderCommand.orderId(), buyOrderCommand.price(), buyOrderCommand.quantity()));
            buyOrderCommands.poll();
        }

        List<RestingOrder> asks = new ArrayList<>();
        while (!sellOrderCommands.isEmpty()) {
            OrderCommandPrioritized sellOrderCommand = sellOrderCommands.peek();
            asks.add(new RestingOrder(sellOrderCommand.orderId(), sellOrderCommand.price(), sellOrderCommand.quantity()));
            sellOrderCommands.poll();
        }

        return new MatchingResult(trades, new OrderBookSnapshot(bids, asks));
    }

    private static Trade getTrade(OrderCommandPrioritized buyOrderCommand, OrderCommandPrioritized sellOrderCommand) {
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