package com.example.challenges.fulfillment.domain;

import java.util.List;

public record FulfillmentRequest(
        List<Warehouse> warehouses,
        List<OrderItem> requestedItems
) {}