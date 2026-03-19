package com.example.challenges.finance;

import com.example.challenges.finance.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LegacyDividendCalculator {

    public DividendPayoutReport calculatePayouts(DividendRequest request) {
        if (request == null) {
            System.out.println("Alert: The input request is null.");
            return new DividendPayoutReport(List.of());
        }

        if (request.positions().isEmpty() || request.events().isEmpty()) {
            return new DividendPayoutReport(List.of());
        }

        Map<String, DividendEvent> dividendEventMap = request.events()
                .stream()
                .filter(event -> {
                    if (event == null || !event.isValid()) {
                        System.out.println("Alert: A null or malformed dividend event was discarded");
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toMap(DividendEvent::ticker,
                        Function.identity(),
                        (existing, replacement) -> existing));

        List<InvestorPayout> payoutsByInvestor = request.positions()
                .stream()
                .filter(position -> {
                    if (position == null || !position.isValid()) {
                        System.out.println("Alert: A null or malformed investor position was discarded");
                        return false;
                    }
                    return true;
                })
                .filter(position -> {
                    DividendEvent event = dividendEventMap.get(position.ticker());
                    return event != null && position.purchasedBefore(event.exDividendDate());
                })
                .collect(Collectors.toMap(InvestorPosition::investorId,
                        position -> position.calculatePayout(dividendEventMap.get(position.ticker()).dividendPerShare()),
                        BigDecimal::add))
                .entrySet().stream()
                .map(entry -> new InvestorPayout(entry.getKey(),
                        entry.getValue().setScale(2, RoundingMode.HALF_UP)))
                .sorted(Comparator.comparing(InvestorPayout::investorId))
                .toList();

        return new DividendPayoutReport(payoutsByInvestor);
    }
}