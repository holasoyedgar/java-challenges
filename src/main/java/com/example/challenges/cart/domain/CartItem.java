package com.example.challenges.cart.domain;

import java.math.BigDecimal;

public record CartItem(String name, BigDecimal price, Integer quantity, String type) {}