package com.example.challenges.finance.domain;

import java.math.BigDecimal;

public record StockTradeDto(String ticker, BigDecimal buyPrice, BigDecimal sellPrice, Integer quantity, String holdingPeriod) { }
