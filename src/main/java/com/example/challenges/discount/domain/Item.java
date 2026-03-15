package com.example.challenges.discount.domain;

import java.math.BigDecimal;

public record Item(String name, BigDecimal price, int quantity) {
    public Item {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price is not valid");
        }
    }
}
