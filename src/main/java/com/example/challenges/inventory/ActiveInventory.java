package com.example.challenges.inventory;

import java.util.HashMap;
import java.util.Map;

public class ActiveInventory {
    private final Map<String, Integer> stock;

    public ActiveInventory(Map<String, Integer> stock) {
        this.stock = stock == null ? new HashMap<>() : new HashMap<>(Map.copyOf(stock));
    }

    public boolean tryConsume(String item, int quantity) {
        int stockQuantity = stock.getOrDefault(item, 0);
        if (stockQuantity >= quantity) {
            stock.put(item, stockQuantity - quantity);
            return true;
        }
        return false;
    }
}
