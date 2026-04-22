package com.example.challenges.trading.domain;

import java.util.List;

public record OrderBookRequest(
        List<OrderCommand> commands
) {}