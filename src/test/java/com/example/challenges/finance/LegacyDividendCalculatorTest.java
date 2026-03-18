package com.example.challenges.finance;

import com.example.challenges.finance.domain.DividendPayoutReport;
import com.example.challenges.finance.domain.DividendRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LegacyDividendCalculatorTest {

    private final ChallengeTestRunner<DividendRequest, DividendPayoutReport> runner = new ChallengeTestRunner<>(
            "dividends",
            DividendRequest.class,
            DividendPayoutReport.class,
            input -> new LegacyDividendCalculator().calculatePayouts(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_single_investor_single_dividend",
                "02_multiple_investors_multiple_dividends",
                "03_purchase_on_or_after_ex_date_no_payout",
                "04_malformed_data_skipped",
                "05_empty_request"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}