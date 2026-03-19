package com.example.challenges.finance.domain;

public record StockTrade(String ticker, Double buyPrice, Double sellPrice, Integer quantity, String holdingPeriod) {}
