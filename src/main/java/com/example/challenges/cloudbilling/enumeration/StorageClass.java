package com.example.challenges.cloudbilling.enumeration;

import java.math.BigDecimal;
import java.util.Optional;

public enum StorageClass {
    STANDARD(new BigDecimal("0.001"), new BigDecimal("0"), 0),
    GLACIER(new BigDecimal("0.0002"), new BigDecimal("0.01"), 90);

    private final BigDecimal storageCost;
    private final BigDecimal retrievalCost;
    private final int earlyDeletionPenaltyDays;

    StorageClass(BigDecimal storageCost, BigDecimal retrievalCost, int earlyDeletionPenaltyDays) {
        this.storageCost = storageCost;
        this.retrievalCost = retrievalCost;
        this.earlyDeletionPenaltyDays = earlyDeletionPenaltyDays;
    }

    public static Optional<StorageClass> fromTier(String tier) {
        return Optional.of(StorageClass.valueOf(tier.toUpperCase()));
    }

    public BigDecimal calculateStorageCost(int sizeGb, int storageDays) {
        return storageCost
                .multiply(BigDecimal.valueOf(sizeGb))
                .multiply(BigDecimal.valueOf(storageDays));
    }

    public BigDecimal calculateRetrievalCost(int retrievedGb) {
        return retrievalCost
                .multiply(BigDecimal.valueOf(retrievedGb));
    }

    public BigDecimal calculateEarlyDeletionPenalty(int storageDays, int sizeGb) {
        int missingDays = Math.max(0, earlyDeletionPenaltyDays - storageDays);
        return BigDecimal.valueOf(missingDays)
                .multiply(BigDecimal.valueOf(sizeGb))
                .multiply(storageCost);
    }
}
