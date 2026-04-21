package com.example.challenges.fulfillment.domain;

import java.util.List;

public record FulfillmentRequest(
        List<Warehouse> warehouses,
        List<OrderItem> requestedItems
) {
    public FulfillmentRequest {
        if (warehouses == null) {
            throw new IllegalArgumentException("Warehouses are null");
        }
        if (requestedItems == null) {
            throw new IllegalArgumentException("Requested items are null");
        }
        warehouses = List.copyOf(warehouses);
        requestedItems = List.copyOf(requestedItems);
    }
}