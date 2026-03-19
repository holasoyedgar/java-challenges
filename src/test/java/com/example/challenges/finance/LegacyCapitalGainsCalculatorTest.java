package com.example.challenges.finance;

import com.example.challenges.finance.domain.TaxRequest;
import com.example.challenges.finance.domain.TaxResult;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LegacyCapitalGainsCalculatorTest {

    private final ChallengeTestRunner<TaxRequest, TaxResult> runner = new ChallengeTestRunner<>(
            "capital_gains",
            TaxRequest.class,
            TaxResult.class,
            input -> new LegacyCapitalGainsCalculator().calculateTaxes(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_single_trade_long_term",
                "02_multiple_trades_mixed_periods",
                "03_net_loss_zero_tax",
                "04_malformed_and_invalid_quantity",
                "05_empty_request"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}