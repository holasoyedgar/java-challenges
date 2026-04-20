package com.example.challenges.cloudbilling;

import com.example.challenges.cloudbilling.domain.CloudBillingReceipt;
import com.example.challenges.cloudbilling.domain.CloudBillingRequest;
import com.example.challenges.cloudbilling.enumeration.StorageClass;

import java.math.BigDecimal;

import static com.example.challenges.cloudbilling.constant.Constants.*;

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
                    BigDecimal storageCost = storageClass.getStorageCost()
                            .multiply(BigDecimal.valueOf(request.sizeGb()))
                            .multiply(BigDecimal.valueOf(request.storageDays()))
                            .setScale(SCALE, ROUNDING_MODE);

                    BigDecimal retrievalCost = storageClass.getRetrievalCost()
                            .multiply(BigDecimal.valueOf(request.retrievedGb()))
                            .setScale(SCALE, ROUNDING_MODE);

                    BigDecimal earlyDeletionPenalty = BigDecimal.valueOf(Math.max(0, storageClass.getEarlyDeletionPenaltyDays() - request.storageDays()))
                            .multiply(BigDecimal.valueOf(request.sizeGb()))
                            .multiply(storageClass.getStorageCost())
                            .setScale(SCALE, ROUNDING_MODE);

                    BigDecimal totalCost = storageCost.add(retrievalCost).add(earlyDeletionPenalty).setScale(SCALE, ROUNDING_MODE);

                    return new CloudBillingReceipt(request.bucketId(), storageCost, retrievalCost, earlyDeletionPenalty, totalCost);
                })
                .orElse(CloudBillingReceipt.defaultReceipt(request.bucketId()));
    }
}