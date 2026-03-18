package com.example.challenges.finance;

import com.example.challenges.finance.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegacyDividendCalculator {

    public DividendPayoutReport calculatePayouts(DividendRequest request) {
        if (request == null || request.positions() == null || request.events() == null) {
            return new DividendPayoutReport(new ArrayList<>());
        }

        Map<String, BigDecimal> payoutsByInvestor = new HashMap<>();

        for (int i = 0; i < request.events().size(); i++) {
            DividendEvent event = request.events().get(i);
            if (event == null || event.ticker() == null || event.dividendPerShare() == null || event.exDividendDate() == null) {
                continue;
            }

            LocalDate exDate = LocalDate.parse(event.exDividendDate());

            for (int j = 0; j < request.positions().size(); j++) {
                InvestorPosition pos = request.positions().get(j);
                if (pos == null || pos.investorId() == null || pos.ticker() == null || pos.purchaseDate() == null) {
                    continue;
                }

                if (pos.ticker().equals(event.ticker())) {
                    LocalDate pDate = LocalDate.parse(pos.purchaseDate());

                    if (pDate.isBefore(exDate)) {
                        BigDecimal payout = event.dividendPerShare().multiply(new BigDecimal(pos.shares()));

                        if (payoutsByInvestor.containsKey(pos.investorId())) {
                            BigDecimal current = payoutsByInvestor.get(pos.investorId());
                            payoutsByInvestor.put(pos.investorId(), current.add(payout));
                        } else {
                            payoutsByInvestor.put(pos.investorId(), payout);
                        }
                    }
                }
            }
        }

        List<InvestorPayout> results = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : payoutsByInvestor.entrySet()) {
            BigDecimal finalPayout = entry.getValue().setScale(2, RoundingMode.HALF_UP);
            results.add(new InvestorPayout(entry.getKey(), finalPayout));
        }

        results.sort((p1, p2) -> p1.investorId().compareTo(p2.investorId()));

        return new DividendPayoutReport(results);
    }
}