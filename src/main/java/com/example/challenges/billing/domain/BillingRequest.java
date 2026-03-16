package com.example.challenges.billing.domain;

import java.util.List;

public record BillingRequest(List<Subscription> subscriptions) {}
