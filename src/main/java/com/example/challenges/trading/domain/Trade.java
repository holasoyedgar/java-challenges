package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record Trade(
        String makerOrderId,
        String takerOrderId,
        BigDecimal executionPrice,
        int executedQuantity
) {}