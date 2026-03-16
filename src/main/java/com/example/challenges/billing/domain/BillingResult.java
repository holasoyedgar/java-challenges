package com.example.challenges.billing.domain;

import java.util.List;

public record BillingResult(List<UserBillingSummary> summaries) {}
