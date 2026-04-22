package com.example.challenges.bom;

import com.example.challenges.bom.domain.BomReceipt;
import com.example.challenges.bom.domain.BomRequest;
import com.example.challenges.bom.domain.BomEdge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BomExplosionEngine {
    public BomReceipt explode(BomRequest request) {
        if (request == null || request.targetProductId() == null || request.targetProductId().isBlank()) {
            return new BomReceipt(Map.of());
        }

        Map<String, List<BomEdge>> adjacencyList = request.edges().stream()
                .collect(Collectors.groupingBy(BomEdge::parentId));

        Map<String, Integer> rawMaterials = traverse(request.targetProductId(), request.requestedQuantity(), adjacencyList);

        return new BomReceipt(rawMaterials);
    }

    private Map<String, Integer> traverse(String currentId, int currentQty, Map<String, List<BomEdge>> adjacencyList) {
        List<BomEdge> edges = adjacencyList.getOrDefault(currentId, List.of());
        if (edges.isEmpty()) {
            return Map.of(currentId, currentQty);
        }
        Map<String, Integer> subTreeAccumulator = new HashMap<>();
        for (BomEdge edge : edges) {
            Map<String, Integer> childRequirements = traverse(edge.childId(), currentQty * edge.quantityMultiplier(), adjacencyList);
            childRequirements.forEach((key, value) -> subTreeAccumulator.merge(key, value, Integer::sum));
        }
        return subTreeAccumulator;
    }
}