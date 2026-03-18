package com.example.challenges.metrics;

import com.example.challenges.metrics.domain.MetricReportRequest;
import com.example.challenges.metrics.domain.MetricReportResult;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LegacyCloudWatchMetricsProcessorTest {

    private final ChallengeTestRunner<MetricReportRequest, MetricReportResult> runner = new ChallengeTestRunner<>(
            "cloudwatch_metrics",
            MetricReportRequest.class,
            MetricReportResult.class,
            input -> new LegacyCloudWatchMetricsProcessor().processMetrics(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_empty_metrics",
                "02_valid_percent_and_count",
                "03_alarm_threshold_exceeded",
                "04_ignored_units",
                "05_malformed_data_ignored",
                "06_null_metrics"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}