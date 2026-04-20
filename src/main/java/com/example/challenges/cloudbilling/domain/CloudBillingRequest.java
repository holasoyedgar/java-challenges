package com.example.challenges.cloudbilling.domain;

public record CloudBillingRequest(
        String bucketId,
        String tier,
        int sizeGb,
        int storageDays,
        int retrievedGb
) {}