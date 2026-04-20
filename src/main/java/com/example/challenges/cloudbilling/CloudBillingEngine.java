package com.example.challenges.cloudbilling;

import com.example.challenges.cloudbilling.domain.CloudBillingReceipt;
import com.example.challenges.cloudbilling.domain.CloudBillingRequest;
import com.example.challenges.cloudbilling.enumeration.StorageClass;

import java.math.BigDecimal;

public class CloudBillingEngine {

    public CloudBillingReceipt calculateMonthlyBill(CloudBillingRequest request) {
        if (request == null) {
            return CloudBillingReceipt.defaultReceipt();
        }
        if (!request.isValid()) {
            return CloudBillingReceipt.defaultReceipt(request.bucketId());
        }
        return StorageClass.fromTier(request.tier())
                .map(storageClass -> {
                    BigDecimal storageCost = storageClass.calculateStorageCost(request.sizeGb(), request.storageDays());

                    BigDecimal retrievalCost = storageClass.calculateRetrievalCost(request.retrievedGb());

                    BigDecimal earlyDeletionPenalty = storageClass.calculateEarlyDeletionPenalty(request.storageDays(), request.sizeGb());

                    return new CloudBillingReceipt(request.bucketId(), storageCost, retrievalCost, earlyDeletionPenalty);
                })
                .orElse(CloudBillingReceipt.defaultReceipt(request.bucketId()));
    }
}