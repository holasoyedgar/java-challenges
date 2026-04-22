package com.example.challenges.bom.domain;

public record BomEdge(
        String parentId,
        String childId,
        int quantityMultiplier
) {}