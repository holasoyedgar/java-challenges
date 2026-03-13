package com.example.challenges.salarycalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class LegacySalaryCalculator {

    public BigDecimal calculateTotalSalary(List<Department> departments) {
        if (departments == null) {
            return BigDecimal.ZERO;
        }
        return departments.stream()
                .filter(Objects::nonNull)
                .filter(department -> department.employees() != null)
                .flatMap(department -> department.employees().stream())
                .filter(Objects::nonNull)
                .map(Employee::salary)
                .filter(Objects::nonNull)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}