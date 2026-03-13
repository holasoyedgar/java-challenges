package com.example.challenges.salarycalculator;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class LegacySalaryCalculatorTest {

    record DepartmentWrapper(List<Department> departments) {}

    private final ChallengeTestRunner<DepartmentWrapper, BigDecimal> runner = new ChallengeTestRunner<>(
            "salary_calculator",
            DepartmentWrapper.class,
            BigDecimal.class,
            input -> new LegacySalaryCalculator()
                    .calculateTotalSalary(input.departments())
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_empty_list",
                "02_single_department_single_employee",
                "03_single_department_multiple_employees",
                "04_multiple_departments",
                "05_edge_cases_null_lists"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}