package com.example.challenges.trading.domain;

import java.util.List;

public record MatchingResult(
        List<Trade> trades,
        OrderBookSnapshot snapshot
) {}