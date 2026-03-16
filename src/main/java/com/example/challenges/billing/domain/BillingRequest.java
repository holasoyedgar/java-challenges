package com.example.challenges.billing.domain;

import java.util.List;
import java.util.Objects;

public record BillingRequest(List<Subscription> subscriptions) {
    public BillingRequest {
        subscriptions = subscriptions != null ? subscriptions.stream().filter(Objects::nonNull).filter(Subscription::isValid).toList() : List.of();
    }
}
