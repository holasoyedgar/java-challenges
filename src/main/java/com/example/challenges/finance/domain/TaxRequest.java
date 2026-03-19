package com.example.challenges.finance.domain;

import java.util.List;

public record TaxRequest(List<StockTrade> trades) {
    public TaxRequest {
        if (trades == null) {
            throw new IllegalArgumentException("The input is corrupt, the trades list is null.");
        }

        // Copy trades to ensure immutability.
        trades = trades.stream().toList();
    }
}
