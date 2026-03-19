package com.example.challenges.finance.domain;

import java.util.List;

public record TaxRequest(List<StockTrade> trades) {}
