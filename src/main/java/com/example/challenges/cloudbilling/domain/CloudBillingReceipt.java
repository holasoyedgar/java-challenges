package com.example.challenges.cloudbilling.domain;

import java.math.BigDecimal;

public record CloudBillingReceipt(
        String bucketId,
        BigDecimal storageCost,
        BigDecimal retrievalCost,
        BigDecimal earlyDeletionPenalty,
        BigDecimal totalCost
) {}