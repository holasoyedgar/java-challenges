package com.example.challenges.cloudbilling.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record CloudBillingReceipt(
        String bucketId,
        BigDecimal storageCost,
        BigDecimal retrievalCost,
        BigDecimal earlyDeletionPenalty,
        BigDecimal totalCost
) {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public CloudBillingReceipt(String bucketId, BigDecimal storageCost, BigDecimal retrievalCost, BigDecimal earlyDeletionPenalty) {
        this(bucketId, storageCost, retrievalCost, earlyDeletionPenalty, BigDecimal.ZERO);
    }

    public CloudBillingReceipt {
        storageCost = storageCost != null ? storageCost.setScale(SCALE, ROUNDING_MODE) : BigDecimal.ZERO;
        retrievalCost = retrievalCost != null ? retrievalCost.setScale(SCALE, ROUNDING_MODE) : BigDecimal.ZERO;
        earlyDeletionPenalty = earlyDeletionPenalty != null ? earlyDeletionPenalty.setScale(SCALE, ROUNDING_MODE) : BigDecimal.ZERO;

        totalCost = storageCost.add(retrievalCost).add(earlyDeletionPenalty);
    }

    public static CloudBillingReceipt defaultReceipt() {
        return defaultReceipt(null);
    }

    public static CloudBillingReceipt defaultReceipt(String bucketId) {
        return new CloudBillingReceipt(bucketId,
                BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE),
                BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE),
                BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE),
                BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE));
    }
}