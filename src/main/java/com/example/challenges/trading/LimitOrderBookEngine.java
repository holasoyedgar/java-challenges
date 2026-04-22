package com.example.challenges.trading;

import com.example.challenges.trading.domain.*;

import java.util.*;

public class LimitOrderBookEngine {

    public MatchingResult processOrders(OrderBookRequest request) {
        if (request == null || request.commands() == null) {
            return new MatchingResult(List.of(), new OrderBookSnapshot(List.of(), List.of()));
        }

        CategorizedOrderCommands categorizedOrderCommands = CategorizedOrderCommands.categorize(request.commands());

        Queue<PrioritizedOrderCommand> buyOrderCommands = categorizedOrderCommands.getBuyOrderCommands();

        Queue<PrioritizedOrderCommand> sellOrderCommands = categorizedOrderCommands.getSellOrderCommands();

        List<Trade> trades = getTrades(buyOrderCommands, sellOrderCommands);

        List<RestingOrder> bids = fillOutBids(buyOrderCommands);

        List<RestingOrder> asks = fillOutAsks(sellOrderCommands);

        return new MatchingResult(trades, new OrderBookSnapshot(bids, asks));
    }

    private List<Trade> getTrades(Queue<PrioritizedOrderCommand> buyOrderCommands, Queue<PrioritizedOrderCommand> sellOrderCommands) {
        List<Trade> trades = new ArrayList<>();

        while (!buyOrderCommands.isEmpty() && !sellOrderCommands.isEmpty()) {
            PrioritizedOrderCommand buyOrderCommand = buyOrderCommands.peek();
            PrioritizedOrderCommand sellOrderCommand = sellOrderCommands.peek();
            if (buyOrderCommand.price().compareTo(sellOrderCommand.price()) < 0) {
                break;
            }
            Trade trade = Trade.fromPrioritizedOrderCommands(buyOrderCommand, sellOrderCommand);
            trades.add(trade);
            buyOrderCommands.poll();
            if (buyOrderCommand.quantity() > trade.executedQuantity()) {
                buyOrderCommands.offer(buyOrderCommand.withSubtractedExecutedQuantity(trade.executedQuantity()));
            }

            sellOrderCommands.poll();
            if (sellOrderCommand.quantity() > trade.executedQuantity()) {
                sellOrderCommands.offer(sellOrderCommand.withSubtractedExecutedQuantity(trade.executedQuantity()));
            }
        }

        return trades;
    }

    private List<RestingOrder> fillOutBids(Queue<PrioritizedOrderCommand> buyOrderCommands) {
        return fillOutRestingOrders(buyOrderCommands);
    }

    private List<RestingOrder> fillOutAsks(Queue<PrioritizedOrderCommand> sellOrderCommands) {
        return fillOutRestingOrders(sellOrderCommands);
    }

    private List<RestingOrder> fillOutRestingOrders(Queue<PrioritizedOrderCommand> orderCommands) {
        List<RestingOrder> restingOrders = new ArrayList<>();
        while (!orderCommands.isEmpty()) {
            PrioritizedOrderCommand orderCommand = orderCommands.peek();
            restingOrders.add(RestingOrder.fromPrioritizedOrderCommand(orderCommand));
            orderCommands.poll();
        }
        return restingOrders;
    }
}