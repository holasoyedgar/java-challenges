package com.example.challenges.finance;

import com.example.challenges.finance.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LegacyDividendCalculator {

    public DividendPayoutReport calculatePayouts(DividendRequest request) {
        if (request == null || !request.isComplete()) {
            System.out.println("Alert: The client sent a null request or incomplete data.");
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
                        Function.identity()));

        List<InvestorPayout> results = request.positions()
                .stream()
                .filter(position -> {
                    if (position == null || !position.isValid()) {
                        System.out.println("Alert: A null or malformed investor position was discarded");
                        return false;
                    }
                    return true;
                })
                .map(position -> {
                    DividendEvent event = dividendEventMap.get(position.ticker());

                    if (event == null || !position.purchasedBefore(event.exDividendDate())) {
                        return null;
                    }

                    return Map.entry(position.investorId(), position.calculatePayout(event.dividendPerShare()));
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, BigDecimal::add))
                .entrySet()
                .stream()
                .map(entry -> {
                    BigDecimal finalPayout = entry.getValue().setScale(2, RoundingMode.HALF_UP);
                    return new InvestorPayout(entry.getKey(), finalPayout);
                })
                .sorted(Comparator.comparing(InvestorPayout::investorId))
                .toList();

        return new DividendPayoutReport(results);
    }
}