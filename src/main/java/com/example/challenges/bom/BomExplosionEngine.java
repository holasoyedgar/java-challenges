package com.example.challenges.bom;

import com.example.challenges.bom.domain.BomReceipt;
import com.example.challenges.bom.domain.BomRequest;
import com.example.challenges.bom.domain.BomEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BomExplosionEngine {

    // ANTI-PATTERN: Global state mutability. Fails under concurrent requests.
    private final Map<String, Integer> rawMaterialAccumulator = new HashMap<>();

    public BomReceipt explode(BomRequest request) {
        rawMaterialAccumulator.clear();

        if (request == null || request.edges() == null || request.edges().isEmpty()) {
            Map<String, Integer> singleElement = new HashMap<>();
            if (request != null && request.targetProductId() != null && !request.targetProductId().isBlank()) {
                singleElement.put(request.targetProductId(), request.requestedQuantity());
            }
            return new BomReceipt(singleElement);
        }

        // Inefficient recursive traversal
        traverse(request.targetProductId(), request.requestedQuantity(), request.edges());

        // Inefficient post-processing cleanup
        List<String> intermediateNodes = new ArrayList<>();
        for (String nodeId : rawMaterialAccumulator.keySet()) {
            for (BomEdge edge : request.edges()) {
                if (edge.parentId().equals(nodeId)) {
                    intermediateNodes.add(nodeId);
                    break;
                }
            }
        }

        for (String intermediate : intermediateNodes) {
            rawMaterialAccumulator.remove(intermediate);
        }

        return new BomReceipt(new HashMap<>(rawMaterialAccumulator));
    }

    private void traverse(String currentId, int currentQty, List<BomEdge> edges) {
        boolean isRawMaterial = true;

        for (BomEdge edge : edges) {
            if (edge.parentId().equals(currentId)) {
                isRawMaterial = false;
                traverse(edge.childId(), currentQty * edge.quantityMultiplier(), edges);
            }
        }

        rawMaterialAccumulator.put(currentId, rawMaterialAccumulator.getOrDefault(currentId, 0) + currentQty);
    }
}