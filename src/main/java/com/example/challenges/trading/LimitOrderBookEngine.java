package com.example.challenges.trading;

import com.example.challenges.trading.domain.*;

import java.util.List;

public class LimitOrderBookEngine {

    public MatchingResult processOrders(OrderBookRequest request) {
        if (request == null || request.commands() == null) {
            return new MatchingResult(List.of(), new OrderBookSnapshot(List.of(), List.of()));
        }

        // TODO: Implement the limit order book matching algorithm
        // Ensure Price-Time priority and correct generation of Trade records.

        return new MatchingResult(List.of(), new OrderBookSnapshot(List.of(), List.of()));
    }
}