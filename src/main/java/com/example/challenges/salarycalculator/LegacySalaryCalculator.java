package com.example.challenges.salarycalculator;

import java.util.List;

public class LegacySalaryCalculator {

    public int calculateTotalSalary(List<Department> departments) {
        if (departments == null) {
            return 0;
        }

        int totalSalary = 0;

        for (int i = 0; i < departments.size(); i++) {
            Department dept = departments.get(i);
            if (dept != null && dept.getEmployees() != null) {
                for (int j = 0; j < dept.getEmployees().size(); j++) {
                    Employee emp = dept.getEmployees().get(j);
                    if (emp != null) {
                        totalSalary += emp.getSalary();
                    }
                }
            }
        }

        return totalSalary;
    }
}