package com.example.challenges.discount.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record Order(Customer customer, List<Item> items, String promoCode) {
    public Order {
        items = items != null ? items.stream().filter(Objects::nonNull).toList() : List.of();
    }

    public boolean isValidOrder() {
        return customer != null;
    }

    public BigDecimal calculateTotalCost() {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
