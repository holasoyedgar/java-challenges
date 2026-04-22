package com.example.challenges.trading.domain;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CategorizedOrderCommands {
    private final Queue<PrioritizedOrderCommand> buyOrderCommands = new PriorityQueue<>(Comparator.comparing(PrioritizedOrderCommand::price)
            .reversed()
            .thenComparing(PrioritizedOrderCommand::orderId));

    private final Queue<PrioritizedOrderCommand> sellOrderCommands = new PriorityQueue<>(Comparator.comparing(PrioritizedOrderCommand::price)
            .thenComparing(PrioritizedOrderCommand::orderId));

    private CategorizedOrderCommands(List<OrderCommand> commands) {
        for (int i = 0; i < commands.size(); i++) {
            OrderCommand orderCommand = commands.get(i);
            PrioritizedOrderCommand prioritizedOrderCommand = new PrioritizedOrderCommand(orderCommand.orderId(), orderCommand.price(), orderCommand.quantity(), i);
            if (orderCommand.side().equals(OrderSide.BUY)) {
                buyOrderCommands.offer(prioritizedOrderCommand);
            } else {
                sellOrderCommands.offer(prioritizedOrderCommand);
            }
        }
    }

    public static CategorizedOrderCommands categorize(List<OrderCommand> commands) {
        return new CategorizedOrderCommands(commands);
    }

    public Queue<PrioritizedOrderCommand> getBuyOrderCommands() {
        return buyOrderCommands;
    }

    public Queue<PrioritizedOrderCommand> getSellOrderCommands() {
        return sellOrderCommands;
    }
}
