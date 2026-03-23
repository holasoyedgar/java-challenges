package com.example.challenges.cart.domain;

import java.util.List;

public record CartRequest(List<CartItem> items, String couponCode) {}