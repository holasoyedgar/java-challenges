package com.example.challenges.fulfillment.domain;

import java.util.List;

public record Shipment(
        String warehouseId,
        List<OrderItem> fulfilledItems
) {
    public static Shipment fromMapEntry(String warehouseId, List<OrderItem> fulfilledItems) {
        return new Shipment(warehouseId, fulfilledItems);
    }
}