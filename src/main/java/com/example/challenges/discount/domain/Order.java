package com.example.challenges.discount.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record Order(Customer customer, List<Item> items, String promoCode) {
    public Order {
        items = items != null ? items.stream().filter(Objects::nonNull)
                .filter(item -> item.price() != null).toList() : List.of();
    }

    public boolean isValidOrder() {
        return customer != null &&
                items.stream(). // Negative prices.
                        noneMatch(item -> item.price().compareTo(BigDecimal.ZERO) < 0);
    }

    public BigDecimal totalCost() {
        return items.stream()
                .map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
