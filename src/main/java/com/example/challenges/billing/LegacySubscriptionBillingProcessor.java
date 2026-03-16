package com.example.challenges.billing;

import com.example.challenges.billing.domain.BillingRequest;
import com.example.challenges.billing.domain.BillingResult;
import com.example.challenges.billing.domain.Subscription;
import com.example.challenges.billing.domain.UserBillingSummary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegacySubscriptionBillingProcessor {

    public BillingResult processBilling(BillingRequest request) {
        if (request == null || request.subscriptions() == null) {
            return new BillingResult(new ArrayList<>());
        }

        Map<String, BigDecimal> userTotals = new HashMap<>();

        for (int i = 0; i < request.subscriptions().size(); i++) {
            Subscription sub = request.subscriptions().get(i);
            if (sub != null && sub.userId() != null && sub.status() != null && sub.monthlyRate() != null) {
                if (sub.status().equals("ACTIVE") || sub.status().equals("PAST_DUE")) {
                    BigDecimal currentTotal = userTotals.getOrDefault(sub.userId(), BigDecimal.ZERO);
                    BigDecimal charge = sub.monthlyRate();

                    if (sub.status().equals("PAST_DUE")) {
                        if (sub.monthsOverdue() > 0) {
                            BigDecimal penalty = charge.multiply(new BigDecimal("0.15"))
                                    .multiply(new BigDecimal(sub.monthsOverdue()));
                            charge = charge.add(penalty);
                        }
                    }
                    userTotals.put(sub.userId(), currentTotal.add(charge));
                }
            }
        }

        List<UserBillingSummary> summaries = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : userTotals.entrySet()) {
            summaries.add(new UserBillingSummary(entry.getKey(), entry.getValue()));
        }

        return new BillingResult(summaries);
    }
}
