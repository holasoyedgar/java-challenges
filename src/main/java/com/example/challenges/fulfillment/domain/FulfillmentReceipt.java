package com.example.challenges.fulfillment.domain;

import java.math.BigDecimal;
import java.util.List;

public record FulfillmentReceipt(
        List<Shipment> shipments,
        List<OrderItem> unfulfilledItems,
        BigDecimal totalShippingCost
) {}