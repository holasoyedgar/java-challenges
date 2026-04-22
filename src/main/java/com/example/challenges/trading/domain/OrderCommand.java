package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record OrderCommand(
        String orderId,
        OrderSide side,
        BigDecimal price,
        int quantity
) {}