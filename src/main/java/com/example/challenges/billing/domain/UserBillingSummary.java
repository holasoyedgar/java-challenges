package com.example.challenges.billing.domain;

import java.math.BigDecimal;

public record UserBillingSummary(String userId, BigDecimal totalBilled) {}
