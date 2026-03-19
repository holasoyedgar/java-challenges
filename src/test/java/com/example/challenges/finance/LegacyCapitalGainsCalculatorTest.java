package com.example.challenges.finance;

import com.example.challenges.finance.domain.TaxRequestDto;
import com.example.challenges.finance.domain.TaxResult;
import com.example.challenges.finance.enumeration.HoldingPeriod;
import com.example.challenges.finance.strategy.ExemptTaxRule;
import com.example.challenges.finance.strategy.HoldingPeriodTaxRule;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class LegacyCapitalGainsCalculatorTest {

    private final ChallengeTestRunner<TaxRequestDto, TaxResult> runner = new ChallengeTestRunner<>(
            "capital_gains",
            TaxRequestDto.class,
            TaxResult.class,
            input -> new LegacyCapitalGainsCalculator(List.of(
                    new HoldingPeriodTaxRule(HoldingPeriod.SHORT_TERM, new BigDecimal("0.2")),
                    new ExemptTaxRule(),
                    new HoldingPeriodTaxRule(HoldingPeriod.LONG_TERM, new BigDecimal("0.1"))
            )).calculateTaxes(input)
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