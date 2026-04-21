package com.example.challenges.fulfillment.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public record FulfillmentReceipt(
        List<Shipment> shipments,
        List<OrderItem> unfulfilledItems,
        BigDecimal totalShippingCost
) {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public FulfillmentReceipt {
           totalShippingCost = totalShippingCost != null ?
                   totalShippingCost.setScale(SCALE, ROUNDING_MODE) :
                    BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
    }
}