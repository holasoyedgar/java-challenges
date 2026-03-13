package com.example.challenges.salarycalculator;

import java.math.BigDecimal;

public record Employee (String name, BigDecimal salary) {
    public Employee {
        salary = salary == null ? BigDecimal.ZERO : salary;
    }
}
