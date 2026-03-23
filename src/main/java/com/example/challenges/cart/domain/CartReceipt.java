package com.example.challenges.cart.domain;

import java.math.BigDecimal;

public record CartReceipt(BigDecimal finalTotal, BigDecimal totalDiscount) {}