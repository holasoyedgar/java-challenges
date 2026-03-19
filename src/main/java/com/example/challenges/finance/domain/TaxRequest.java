package com.example.challenges.finance.domain;

import java.util.List;

public record TaxRequest(List<StockTrade> trades) {
    // Option 1: Throwing an exception in case of null trades
    /*
    public TaxRequest {
        if (trades == null) {
            throw new IllegalArgumentException("The input is corrupt, the trades list is null.");
        }

        // Copy trades to ensure immutability.
        trades = trades.stream().toList();
    }
    */
    // Option 2: If trades are not null, copy them.
    // The requirement specifies that null or incomplete transactions should be ignored.
    public TaxRequest {
        if (trades != null) {
            // Copy trades to ensure immutability.
            trades = trades.stream().toList();
        }
    }

    public boolean hasTrades() {
        return trades != null && !trades.isEmpty();
    }
}
