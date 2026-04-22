package com.example.challenges.bom.domain;

import java.util.HashMap;
import java.util.Map;

public record BomReceipt(
        Map<String, Integer> rawMaterialRequirements
) {
    public static BomReceipt defaultBomReceipt(BomRequest request) {
        Map<String, Integer> singleElement = new HashMap<>();
        if (request != null && request.nonBlankTargetProductId()) {
            singleElement.put(request.targetProductId(), request.requestedQuantity());
        }
        return new BomReceipt(singleElement);
    }
}