package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OrderFulfillmentEngine {

    public FulfillmentReceipt processOrder(FulfillmentRequest request) {
        Queue<Warehouse> orderedWarehouses = new PriorityQueue<>(Comparator.comparingInt(Warehouse::distance));
        for (Warehouse warehouse : request.warehouses()) {
            orderedWarehouses.offer(warehouse);
        }

        Map<String, List<OrderItem>> fulfilledItemsPerWarehouse = new HashMap<>();
        List<OrderItem> unfulfilledItems = new ArrayList<>();
        BigDecimal totalShippingCost = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        for (OrderItem orderItem : request.requestedItems()) {
            int pendingQuantity = orderItem.quantity();

            for (Warehouse warehouse : orderedWarehouses) {
                if (warehouse.inventory().containsKey(orderItem.productId())) {
                    int warehouseInventory = warehouse.inventory().get(orderItem.productId());
                    int fulfilledQuantity = Math.min(pendingQuantity, warehouseInventory);
                    pendingQuantity = Math.max(0, pendingQuantity - warehouseInventory);
                    warehouseInventory = warehouseInventory - fulfilledQuantity;
                    if (warehouseInventory == 0) {
                        warehouse.inventory().remove(orderItem.productId());
                    } else {
                        warehouse.inventory().put(orderItem.productId(), warehouseInventory);
                    }
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
                                .setScale(2, RoundingMode.HALF_UP);
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