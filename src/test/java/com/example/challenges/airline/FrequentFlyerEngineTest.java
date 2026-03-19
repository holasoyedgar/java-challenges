package com.example.challenges.airline;

import com.example.challenges.airline.domain.FlightBatchRequest;
import com.example.challenges.airline.domain.LoyaltyReport;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FrequentFlyerEngineTest {

    private final ChallengeTestRunner<FlightBatchRequest, LoyaltyReport> runner = new ChallengeTestRunner<>(
            "airline",
            FlightBatchRequest.class,
            LoyaltyReport.class,
            input -> new FrequentFlyerEngine().processFlights(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_single_passenger_bronze",
                "02_multiple_passengers_tier_upgrades",
                "03_invalid_and_malformed_records_skipped",
                "04_empty_request",
                "05_tie_breaker_sorting"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}