package com.example.challenges.discount.domain;

import java.util.List;

public record Order(Customer customer, List<Item> items, String promoCode) { }
