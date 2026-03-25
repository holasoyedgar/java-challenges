package com.example.challenges.cart.domain;

import java.math.BigDecimal;

public record CartTotals(BigDecimal subtotal, BigDecimal totalDiscount) {
    public CartTotals add(CartTotals other) {
        return new CartTotals(this.subtotal.add(other.subtotal),
                this.totalDiscount.add(other.totalDiscount));
    }
}
