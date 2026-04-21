package com.example.challenges.fulfillment.domain;

import java.util.Map;

public record Warehouse(
        String id,
        int distance,
        Map<String, Integer> inventory
) {}