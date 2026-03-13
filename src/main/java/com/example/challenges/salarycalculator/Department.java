package com.example.challenges.salarycalculator;

import java.util.List;
import java.util.Objects;

public record Department (String name, List<Employee> employees) {
    public Department {
        employees = employees == null ? List.of() : employees.stream().filter(Objects::nonNull).toList();
    }
}
