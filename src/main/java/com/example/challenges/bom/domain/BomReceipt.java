package com.example.challenges.bom.domain;

import java.util.Map;

public record BomReceipt(
        Map<String, Integer> rawMaterialRequirements
) {}