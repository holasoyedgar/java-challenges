package com.example.challenges.fulfillment.domain;

import java.util.Map;

public record Warehouse(
        String id,
        int distance,
        Map<String, Integer> inventory
) {
    public Warehouse {
        if (id == null) {
            throw new IllegalArgumentException("The warehouse id is null");
        }
        if (distance < 0) {
            throw new IllegalArgumentException(String.format("The distance of %s is negative", id));
        }
        if (inventory == null) {
            throw new IllegalArgumentException(String.format("The inventory of %s is null", id));
        }
        if (inventory.values()
                .stream()
                .anyMatch(quantity -> quantity < 0)) {
            throw new IllegalArgumentException("Some quantities are negative.");
        }
    }
}