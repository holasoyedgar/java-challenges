package com.example.challenges.trading;

import com.example.challenges.trading.domain.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiPredicate;

public class LimitOrderBookEngine {

    private record MatchExecution(PrioritizedOrderCommand remainder, List<Trade> trades) {
    }

    public MatchingResult processOrders(OrderBookRequest request) {
        if (request == null || request.commands() == null) {
            return new MatchingResult(List.of(), new OrderBookSnapshot(List.of(), List.of()));
        }

        PriorityQueue<PrioritizedOrderCommand> bids = new PriorityQueue<>(Comparator.comparing(PrioritizedOrderCommand::price)
                .reversed()
                .thenComparingInt(PrioritizedOrderCommand::order));

        PriorityQueue<PrioritizedOrderCommand> asks = new PriorityQueue<>(Comparator.comparing(PrioritizedOrderCommand::price)
                .thenComparingInt(PrioritizedOrderCommand::order));

        List<Trade> allTrades = new ArrayList<>();

        for (int order = 0; order < request.commands().size(); order++) {
            OrderCommand orderCommand = request.commands().get(order);
            PrioritizedOrderCommand incomingOrder = PrioritizedOrderCommand.fromOrderCommand(orderCommand, order);

            if (orderCommand.side() == OrderSide.BUY) {
                MatchExecution matchExecution = matchOrder(incomingOrder, asks, (taker, maker) -> taker.compareTo(maker) >= 0);
                incomingOrder = matchExecution.remainder();
                allTrades.addAll(matchExecution.trades());
                if (incomingOrder.quantity() > 0) {
                    bids.offer(incomingOrder);
                }
            } else {
                MatchExecution matchExecution = matchOrder(incomingOrder, bids, (taker, maker) -> maker.compareTo(taker) >= 0);
                incomingOrder = matchExecution.remainder();
                allTrades.addAll(matchExecution.trades());
                if (incomingOrder.quantity() > 0) {
                    asks.offer(incomingOrder);
                }
            }
        }

        return new MatchingResult(allTrades, buildSnapshot(bids, asks));
    }

    private MatchExecution matchOrder(PrioritizedOrderCommand taker, Queue<PrioritizedOrderCommand> oppositeQueue, BiPredicate<BigDecimal, BigDecimal> predicate) {
        List<Trade> trades = new ArrayList<>();
        while (taker.quantity() > 0 && !oppositeQueue.isEmpty()) {
            PrioritizedOrderCommand maker = oppositeQueue.peek();
            if (!predicate.test(taker.price(), maker.price())) {
                break;
            }
            int executedQuantity = Math.min(taker.quantity(), maker.quantity());
            Trade trade = new Trade(maker.orderId(),
                    taker.orderId(),
                    maker.price(),
                    executedQuantity);

            trades.add(trade);
            oppositeQueue.poll();
            if (maker.quantity() > executedQuantity) {
                oppositeQueue.offer(maker.withSubtractedExecutedQuantity(executedQuantity));
            }
            taker = taker.withSubtractedExecutedQuantity(executedQuantity);
        }

        return new MatchExecution(taker, trades);
    }

    private OrderBookSnapshot buildSnapshot(PriorityQueue<PrioritizedOrderCommand> bids, PriorityQueue<PrioritizedOrderCommand> asks) {
        List<RestingOrder> bidRestingOrders = bids.stream()
                .sorted(bids.comparator())
                .map(RestingOrder::fromPrioritizedOrderCommand)
                .toList();

        List<RestingOrder> askRestingOrders = asks.stream()
                .sorted(asks.comparator())
                .map(RestingOrder::fromPrioritizedOrderCommand)
                .toList();
        return new OrderBookSnapshot(bidRestingOrders, askRestingOrders);
    }
}