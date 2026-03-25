package com.example.challenges.cart.factory;

import com.example.challenges.cart.strategy.item.*;

import java.util.Map;

public class ItemDiscountFactory {
    private static final Map<String, ItemDiscountStrategy> STRATEGIES = Map.of("ELECTRONICS", new ElectronicsDiscountStrategy(),
            "GROCERIES", new GroceriesDiscountStrategy(),
            "CLOTHING", new ClothingDiscountStrategy());;


    public static ItemDiscountStrategy getStrategy(String itemType) {
        if (itemType == null) {
            return new DefaultItemDiscountStrategy();
        }
        return STRATEGIES.getOrDefault(itemType.toUpperCase(), new DefaultItemDiscountStrategy());
    }
}
