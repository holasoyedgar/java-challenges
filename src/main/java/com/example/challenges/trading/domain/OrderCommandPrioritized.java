package com.example.challenges.trading.domain;

import java.math.BigDecimal;

public record OrderCommandPrioritized(String orderId,
                                      BigDecimal price,
                                      int quantity,
                                      int order) { }
