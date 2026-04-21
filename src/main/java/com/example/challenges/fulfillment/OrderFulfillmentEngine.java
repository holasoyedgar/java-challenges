package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.*;

import java.math.BigDecimal;
import java.util.*;

public class OrderFulfillmentEngine {
    private final ShippingPricingEngine shippingPricingEngine;

    public OrderFulfillmentEngine(ShippingPricingEngine shippingPricingEngine) {
        this.shippingPricingEngine = shippingPricingEngine;
    }

    public FulfillmentReceipt processOrder(FulfillmentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("The request is null");
        }
        List<Warehouse> orderedWarehouses = sortWarehouses(request.warehouses());
        Map<String, Map<String, Integer>> globalInventory = buildGlobalInventory(orderedWarehouses);

        Map<String, List<OrderItem>> fulfilledItemsPerWarehouse = new HashMap<>();
        List<OrderItem> unfulfilledItems = new ArrayList<>();

        for (OrderItem orderItem : request.requestedItems()) {
            int pendingQuantity = orderItem.quantity();

            for (Warehouse warehouse : orderedWarehouses) {
                if (pendingQuantity == 0) {
                    break;
                }
                Map<String, Integer> virtualInventory = globalInventory.get(warehouse.id());
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

        List<Shipment> shipments = buildShipments(fulfilledItemsPerWarehouse);
        BigDecimal totalShippingCost = shippingPricingEngine.calculateTotalShippingCost(shipments);

        return new FulfillmentReceipt(shipments, unfulfilledItems, totalShippingCost);
    }

    private List<Warehouse> sortWarehouses(List<Warehouse> warehouses) {
        return warehouses
                .stream().sorted(Comparator.comparing(Warehouse::distance))
                .toList();
    }

    private Map<String, Map<String, Integer>> buildGlobalInventory(List<Warehouse> orderedWarehouses) {
        Map<String, Map<String, Integer>> globalInventoryMap = new HashMap<>();
        for (Warehouse w : orderedWarehouses) {
            globalInventoryMap.put(w.id(), new HashMap<>(w.inventory()));
        }

        return globalInventoryMap;
    }

    private List<Shipment> buildShipments(Map<String, List<OrderItem>> fulfilledItemsPerWarehouse) {
        return fulfilledItemsPerWarehouse.entrySet()
                .stream()
                .map(entry -> Shipment.fromMapEntry(entry.getKey(), entry.getValue()))
                .toList();
    }
}