package com.example.challenges.bom.domain;

import java.util.List;

public record BomRequest(
        String targetProductId,
        int requestedQuantity,
        List<BomEdge> edges
) {}