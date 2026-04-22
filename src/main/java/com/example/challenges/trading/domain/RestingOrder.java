package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record RestingOrder(
        String orderId,
        BigDecimal price,
        int remainingQuantity
) {}