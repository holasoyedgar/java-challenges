package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.OrderItem;
import com.example.challenges.fulfillment.domain.Shipment;

import java.math.BigDecimal;
import java.util.List;

public class ShippingPricingEngine {
    private final BigDecimal PRICE_PER_SHIPMENT = BigDecimal.TEN;
    private final BigDecimal PRICE_PER_UNIT = BigDecimal.TWO;

    public BigDecimal calculateTotalShippingCost(List<Shipment> shipments) {
        return shipments.stream()
                .map(shipment -> {
                    int totalUnits = shipment.fulfilledItems().stream().mapToInt(OrderItem::quantity).sum();
                    return PRICE_PER_SHIPMENT.add(PRICE_PER_UNIT.multiply(BigDecimal.valueOf(totalUnits)));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
