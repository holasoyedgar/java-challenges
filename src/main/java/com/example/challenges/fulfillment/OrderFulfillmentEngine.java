package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrderFulfillmentEngine {
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public FulfillmentReceipt processOrder(FulfillmentRequest request) {
        List<Warehouse> orderedWarehouses = request.warehouses()
                .stream().sorted(Comparator.comparing(Warehouse::distance))
                .toList();

        Map<String, List<OrderItem>> fulfilledItemsPerWarehouse = new HashMap<>();
        List<OrderItem> unfulfilledItems = new ArrayList<>();
        BigDecimal totalShippingCost = BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);

        for (OrderItem orderItem : request.requestedItems()) {
            int pendingQuantity = orderItem.quantity();

            for (Warehouse warehouse : orderedWarehouses) {
                if (warehouse.inventory().containsKey(orderItem.productId())) {
                    int warehouseInventory = warehouse.inventory().get(orderItem.productId());
                    if (warehouseInventory <= 0) {
                        continue;
                    }
                    int fulfilledQuantity = Math.min(pendingQuantity, warehouseInventory);
                    pendingQuantity = Math.max(0, pendingQuantity - warehouseInventory);
                    warehouseInventory = warehouseInventory - fulfilledQuantity;
                    warehouse.inventory().put(orderItem.productId(), warehouseInventory);

                    if (fulfilledQuantity > 0) {
                        if (fulfilledItemsPerWarehouse.containsKey(warehouse.id())) {
                            List<OrderItem> orderItems = fulfilledItemsPerWarehouse.get(warehouse.id());
                            orderItems.add(new OrderItem(orderItem.productId(), fulfilledQuantity));
                            fulfilledItemsPerWarehouse.put(warehouse.id(), orderItems);
                        } else {
                            totalShippingCost = totalShippingCost
                                    .add(BigDecimal.TEN);
                            fulfilledItemsPerWarehouse.put(warehouse.id(), new ArrayList<>(List.of(new OrderItem(orderItem.productId(), fulfilledQuantity))));
                        }
                        totalShippingCost = totalShippingCost
                                .add(BigDecimal.TWO.multiply(BigDecimal.valueOf(fulfilledQuantity)))
                                .setScale(SCALE, ROUNDING_MODE);
                    }
                }
            }
            if (pendingQuantity > 0) {
                unfulfilledItems.add(new OrderItem(orderItem.productId(), pendingQuantity));
            }
        }
        List<Shipment> shipments = fulfilledItemsPerWarehouse.entrySet()
                .stream()
                .map(entry -> new Shipment(entry.getKey(), entry.getValue()))
                .toList();
        return new FulfillmentReceipt(shipments, unfulfilledItems, totalShippingCost);
    }
}