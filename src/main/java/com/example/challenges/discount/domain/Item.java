package com.example.challenges.discount.domain;

import java.math.BigDecimal;

public record Item(String name, BigDecimal price, int quantity) {}
