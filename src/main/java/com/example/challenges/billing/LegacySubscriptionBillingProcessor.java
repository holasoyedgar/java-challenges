package com.example.challenges.billing;

import com.example.challenges.billing.domain.BillingRequest;
import com.example.challenges.billing.domain.BillingResult;
import com.example.challenges.billing.domain.Subscription;
import com.example.challenges.billing.domain.UserBillingSummary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class LegacySubscriptionBillingProcessor {

    public BillingResult processBilling(BillingRequest request) {
        if (request == null) {
            return new BillingResult(List.of());
        }

        List<UserBillingSummary> summaries = request.subscriptions()
                .stream()
                .filter(subscription -> !subscription.isCancelled())
                .collect(Collectors.groupingBy(Subscription::userId, Collectors.reducing(
                        BigDecimal.ZERO,
                        Subscription::calculateMonthlyCharge,
                        BigDecimal::add
                )))
                .entrySet()
                .stream()
                .map(entry -> new UserBillingSummary(entry.getKey(),
                        entry.getValue().setScale(2, RoundingMode.HALF_UP)))
                .toList();

        return new BillingResult(summaries);
    }
}
