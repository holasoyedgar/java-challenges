package com.example.challenges.trading.domain;

import java.util.List;

public record OrderBookSnapshot(
        List<RestingOrder> bids, // BUY orders (Sorted: Highest Price First, then Oldest)
        List<RestingOrder> asks  // SELL orders (Sorted: Lowest Price First, then Oldest)
) {}