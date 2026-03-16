package com.example.challenges.logs;

import com.example.challenges.logs.domain.AggregationResult;
import com.example.challenges.logs.domain.LogWrapper;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LegacyLogAggregatorTest {

    private final ChallengeTestRunner<LogWrapper, AggregationResult> runner = new ChallengeTestRunner<>(
            "log_aggregator",
            LogWrapper.class,
            AggregationResult.class,
            input -> new LegacyLogAggregator().countErrorsPerModule(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_empty_list",
                "02_mixed_logs",
                "03_empty_messages",
                "04_null_checks",
                "05_all_fatal"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}