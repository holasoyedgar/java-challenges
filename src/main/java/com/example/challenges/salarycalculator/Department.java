package com.example.challenges.salarycalculator;

import java.util.List;

public record Department (String name, List<Employee> employees) {
    public Department {
        employees = employees == null ? List.of() : List.copyOf(employees);
    }
}
