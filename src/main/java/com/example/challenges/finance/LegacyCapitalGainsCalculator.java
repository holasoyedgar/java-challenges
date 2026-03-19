package com.example.challenges.finance;

import com.example.challenges.finance.domain.StockTrade;
import com.example.challenges.finance.domain.TaxReportItem;
import com.example.challenges.finance.domain.TaxRequest;
import com.example.challenges.finance.domain.TaxResult;
import com.example.challenges.finance.strategy.TaxRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LegacyCapitalGainsCalculator {

    private final List<TaxRule> taxRules;
    public LegacyCapitalGainsCalculator(List<TaxRule> taxRules) {
        this.taxRules = taxRules;
    }

    public TaxResult calculateTaxes(TaxRequest request) {
        if (request == null || !request.hasTrades()) {
            return new TaxResult(List.of());
        }

        List<TaxReportItem> taxReport = request.trades()
                .stream()
                .filter(trade -> {
                    if (trade == null || !trade.isValid()) {
                        System.out.println("Alert: Invalid trade");
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toMap(StockTrade::ticker,
                        this::calculateTax,
                        BigDecimal::add))
                .entrySet().stream()
                .map(entry -> {
                    BigDecimal roundedTax = entry.getValue().setScale(2, RoundingMode.HALF_UP);
                    return new TaxReportItem(entry.getKey(), roundedTax);
                })
                .sorted(Comparator.comparing(TaxReportItem::ticker))
                .toList();

        return new TaxResult(taxReport);
    }

    public BigDecimal calculateTax(StockTrade trade) {
        return this.taxRules.stream()
                .filter(rule -> rule.isApplicable(trade))
                .findFirst()
                .map(rule -> rule.calculate(trade))
                .orElse(BigDecimal.ZERO);
    }
}