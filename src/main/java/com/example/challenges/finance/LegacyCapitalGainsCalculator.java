package com.example.challenges.finance;

import com.example.challenges.finance.domain.StockTrade;
import com.example.challenges.finance.domain.TaxReportItem;
import com.example.challenges.finance.domain.TaxRequest;
import com.example.challenges.finance.domain.TaxResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegacyCapitalGainsCalculator {

    public TaxResult calculateTaxes(TaxRequest request) {
        if (request == null || request.trades() == null) {
            return new TaxResult(new ArrayList<>());
        }

        Map<String, Double> taxesByTicker = new HashMap<>();

        for (int i = 0; i < request.trades().size(); i++) {
            StockTrade trade = request.trades().get(i);
            if (trade != null && trade.ticker() != null && trade.buyPrice() != null
                    && trade.sellPrice() != null && trade.quantity() != null && trade.holdingPeriod() != null) {

                if (trade.quantity() > 0) {
                    double profit = (trade.sellPrice() - trade.buyPrice()) * trade.quantity();
                    
                    if (profit > 0) {
                        double tax = 0.0;
                        if (trade.holdingPeriod().equals("LONG_TERM")) {
                            tax = profit * 0.10;
                        } else if (trade.holdingPeriod().equals("SHORT_TERM")) {
                            tax = profit * 0.20;
                        }

                        if (taxesByTicker.containsKey(trade.ticker())) {
                            taxesByTicker.put(trade.ticker(), taxesByTicker.get(trade.ticker()) + tax);
                        } else {
                            taxesByTicker.put(trade.ticker(), tax);
                        }
                    } else {
                        if (!taxesByTicker.containsKey(trade.ticker())) {
                            taxesByTicker.put(trade.ticker(), 0.0);
                        }
                    }
                }
            }
        }

        List<TaxReportItem> results = new ArrayList<>();
        for (Map.Entry<String, Double> entry : taxesByTicker.entrySet()) {
            double roundedTax = Math.round(entry.getValue() * 100.0) / 100.0;
            results.add(new TaxReportItem(entry.getKey(), roundedTax));
        }

        results.sort((a, b) -> a.ticker().compareTo(b.ticker()));

        return new TaxResult(results);
    }
}