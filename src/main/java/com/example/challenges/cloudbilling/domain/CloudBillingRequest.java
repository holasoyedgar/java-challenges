package com.example.challenges.cloudbilling.domain;

public record CloudBillingRequest(
        String bucketId,
        String tier,
        int sizeGb,
        int storageDays,
        int retrievedGb
) {
    public boolean isValid() {
        return bucketId != null && !bucketId.isBlank() &&
                tier != null && !tier.isBlank() &&
                sizeGb >= 0 &&
                storageDays >= 0 &&
                retrievedGb >= 0;
    }
}