package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrderFulfillmentEngine {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public FulfillmentReceipt processOrder(FulfillmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The request is null");
        }
        List<Warehouse> orderedWarehouses = request.warehouses()
                .stream().sorted(Comparator.comparing(Warehouse::distance))
                .toList();

        Map<String, List<OrderItem>> fulfilledItemsPerWarehouse = new HashMap<>();
        List<OrderItem> unfulfilledItems = new ArrayList<>();

        for (OrderItem orderItem : request.requestedItems()) {
            int pendingQuantity = orderItem.quantity();

            for (Warehouse warehouse : orderedWarehouses) {
                if (pendingQuantity == 0) {
                    break;
                }
                Map<String, Integer> virtualInventory = new HashMap<>(warehouse.inventory());
                int availableInventory = virtualInventory.getOrDefault(orderItem.productId(), 0);
                if (availableInventory > 0) {
                    int fulfilledQuantity = Math.min(pendingQuantity, availableInventory);
                    virtualInventory.put(orderItem.productId(), availableInventory - fulfilledQuantity);
                    pendingQuantity -= fulfilledQuantity;

                    fulfilledItemsPerWarehouse.computeIfAbsent(warehouse.id(), k -> new ArrayList<>())
                            .add(new OrderItem(orderItem.productId(), fulfilledQuantity));
                }
            }
            if (pendingQuantity > 0) {
                unfulfilledItems.add(new OrderItem(orderItem.productId(), pendingQuantity));
            }
        }

        List<Shipment> shipments = getListOfShipments(fulfilledItemsPerWarehouse);
        BigDecimal totalShippingCost = calculateTotalShippingCost(shipments);

        return new FulfillmentReceipt(shipments, unfulfilledItems, totalShippingCost);
    }

    private static List<Shipment> getListOfShipments(Map<String, List<OrderItem>> fulfilledItemsPerWarehouse) {
        return fulfilledItemsPerWarehouse.entrySet()
                .stream()
                .map(entry -> new Shipment(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static BigDecimal calculateTotalShippingCost(List<Shipment> shipments) {
        return shipments.stream()
                .map(shipment -> {
                    int totalUnits = shipment.fulfilledItems().stream().mapToInt(OrderItem::quantity).reduce(0, Integer::sum);
                    return BigDecimal.TEN.add(BigDecimal.TWO.multiply(BigDecimal.valueOf(totalUnits)));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(SCALE, ROUNDING_MODE);
    }
}