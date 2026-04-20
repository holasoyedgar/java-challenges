package com.example.challenges.cloudbilling.domain;

import java.math.BigDecimal;

import static com.example.challenges.cloudbilling.constant.Constants.*;

public record CloudBillingReceipt(
        String bucketId,
        BigDecimal storageCost,
        BigDecimal retrievalCost,
        BigDecimal earlyDeletionPenalty,
        BigDecimal totalCost
) {
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