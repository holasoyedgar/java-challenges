package com.example.challenges.fulfillment.domain;

public record OrderItem(
        String productId,
        int quantity
) {
    public OrderItem {
        if (productId == null) {
            throw new IllegalArgumentException("The product id is null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException(String.format("The quantity of the ordered item %s is zero or negative", productId));
        }
    }
}