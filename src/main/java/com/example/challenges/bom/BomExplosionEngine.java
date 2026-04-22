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
        if (request == null || request.nullOrEmptyEdges()) {
            return BomReceipt.defaultBomReceipt(request);
        }

        Map<String, List<BomEdge>> groupedBomEdgesByParentId = request.edges().stream()
                .collect(Collectors.groupingBy(BomEdge::parentId));

        Map<String, Integer> rawMaterialAccumulator = new HashMap<>();

        traverse(request.targetProductId(), request.requestedQuantity(), groupedBomEdgesByParentId, rawMaterialAccumulator);

        return new BomReceipt(rawMaterialAccumulator);
    }

    private void traverse(String currentId, int currentQty, Map<String, List<BomEdge>> groupedBomEdgesByParentId, Map<String, Integer> rawMaterialAccumulator) {
        List<BomEdge> edges = groupedBomEdgesByParentId.getOrDefault(currentId, List.of());
        for (BomEdge edge : edges) {
            if (edge.parentId().equals(currentId)) {
                traverse(edge.childId(), currentQty * edge.quantityMultiplier(), groupedBomEdgesByParentId, rawMaterialAccumulator);
            }
        }
        if (edges.isEmpty()) {
            rawMaterialAccumulator.merge(currentId, currentQty, Integer::sum);
        }
    }
}