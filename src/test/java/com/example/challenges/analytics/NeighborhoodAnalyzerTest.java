package com.example.challenges.analytics;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class NeighborhoodAnalyzerTest {

    record AnalyzerWrapper(List<NeighborhoodAnalyzer.Listing> listings) {}

    private final ChallengeTestRunner<AnalyzerWrapper, NeighborhoodAnalyzer.NeighborhoodStats[]> runner = new ChallengeTestRunner<>(
            "neighborhood_analyzer",
            AnalyzerWrapper.class,
            NeighborhoodAnalyzer.NeighborhoodStats[].class,
            input -> new NeighborhoodAnalyzer()
                    .analyze(input.listings())
                    .toArray(NeighborhoodAnalyzer.NeighborhoodStats[]::new)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_happy_path_grouping",
                "02_inactive_and_null_prices",
                "03_all_null_prices"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}