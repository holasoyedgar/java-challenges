package com.example.challenges.orderprocessor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

// Testability, Maintainability, Extendability, Performance y Correctness
public class OrderProcessor {
    private static final BigDecimal HIGH_PRICE = BigDecimal.valueOf(100);

    public List<String> process(List<Order> orders, List<String> badIds) {
        List<String> result = new ArrayList<>();
        Set<String> badIdsSet = new HashSet<>(badIds);

        for (Order order : orders) {
            if (order == null) {
                continue;
            }
            if (order.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            if (badIdsSet.contains(order.getId())) {
                // Bad order, it's rejected.
                continue;
            }

            // The order object is not mutated.
            OrderType orderType = OrderType.fromCode(order.getType());
            BigDecimal newPrice = orderType.applyDiscount(order.getPrice());

            if (newPrice.compareTo(HIGH_PRICE) > 0) {
                result.add(order.getId() + "-" + newPrice);
            }
        }
        return result;
    }
}

enum OrderType {
    PREMIUM(1, "0.10"),
    REGULAR(2, "0.05"),
    STANDARD(0, "0.00");

    private static final Map<Integer, OrderType> ORDER_TYPE_LOOKUP = new HashMap<>();

    private final int code;
    private final BigDecimal discount;

    static {
        for (OrderType type : values()) {
            ORDER_TYPE_LOOKUP.put(type.code, type);
        }
    }

    OrderType(int code, String discount) {
        this.code = code;
        this.discount = new BigDecimal(discount);
    }

    public static OrderType fromCode(int code) {
        return ORDER_TYPE_LOOKUP.getOrDefault(code, STANDARD);
    }

    public BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(BigDecimal.ONE.subtract(discount)).setScale(2, RoundingMode.HALF_UP);
    }
}

class Order {
    private String id;
    private int type;
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}