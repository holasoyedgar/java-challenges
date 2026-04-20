package com.example.challenges.cloudbilling.enumeration;

import java.math.BigDecimal;
import java.util.Arrays;
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
        return Arrays.stream(StorageClass.values())
                .filter(storageClass -> storageClass.name().equals(tier))
                .findFirst();
    }

    public BigDecimal getStorageCost() {
        return storageCost;
    }

    public BigDecimal getRetrievalCost() {
        return retrievalCost;
    }

    public int getEarlyDeletionPenaltyDays() {
        return earlyDeletionPenaltyDays;
    }
}
