package com.example.challenges.bom.domain;

import java.util.List;

public record BomRequest(
        String targetProductId,
        int requestedQuantity,
        List<BomEdge> edges
) {
    public BomRequest {
        if (requestedQuantity < 1) {
            throw new IllegalArgumentException("The requested quantity must be greater than 0.");
        }
        edges = List.copyOf(edges);
    }
    public boolean nullOrEmptyEdges() {
        return edges == null || edges.isEmpty();
    }

    public boolean nonBlankTargetProductId() {
        return targetProductId != null && !targetProductId.isBlank();
    }
}