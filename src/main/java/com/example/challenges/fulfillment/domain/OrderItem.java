package com.example.challenges.fulfillment.domain;

public record OrderItem(
        String productId,
        int quantity
) {}