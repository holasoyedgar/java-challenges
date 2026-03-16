package com.example.challenges.billing.domain;

import java.math.BigDecimal;

public record Subscription(String userId, String planId, String status, BigDecimal monthlyRate, int monthsOverdue) {}
