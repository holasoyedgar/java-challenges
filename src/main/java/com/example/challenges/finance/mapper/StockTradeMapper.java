package com.example.challenges.finance.mapper;

import com.example.challenges.finance.domain.StockTrade;
import com.example.challenges.finance.domain.StockTradeDto;
import com.example.challenges.finance.enumeration.HoldingPeriod;

public class StockTradeMapper {
    public static StockTrade toDomain(StockTradeDto dto) {
        if (dto == null) {
            return null;
        }

        HoldingPeriod holdingPeriod = null;

        try {
            holdingPeriod = HoldingPeriod.valueOf(dto.holdingPeriod());
        } catch (IllegalArgumentException e) {
            // Ignore null.
        }

        return new StockTrade(dto.ticker(),
                dto.buyPrice(),
                dto.sellPrice(),
                dto.quantity(),
                holdingPeriod);
    }
}
