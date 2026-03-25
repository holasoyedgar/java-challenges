package com.example.challenges.sofipo;

import com.example.challenges.sofipo.domain.InvestmentReceipt;
import com.example.challenges.sofipo.domain.InvestmentRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SofipoYieldCalculatorTest {

    private final ChallengeTestRunner<InvestmentRequest, InvestmentReceipt> runner = new ChallengeTestRunner<>(
            "sofipo",
            InvestmentRequest.class,
            InvestmentReceipt.class,
            input -> new SofipoYieldCalculator().calculateYield(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_standard_below_exempt",
                "02_standard_above_exempt",
                "03_annual_term_exact_exempt",
                "04_invalid_term",
                "05_high_capital_short_term"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}