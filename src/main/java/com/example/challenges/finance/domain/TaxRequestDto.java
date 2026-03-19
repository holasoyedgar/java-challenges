package com.example.challenges.finance.domain;

import java.util.List;

public record TaxRequestDto(List<StockTradeDto> trades) {
    public TaxRequestDto {
        if (trades == null) {
            throw new IllegalArgumentException("The input is corrupt, the trades list is null.");
        }

        // Copy trades to ensure immutability.
        trades = trades.stream().toList();
    }
}
