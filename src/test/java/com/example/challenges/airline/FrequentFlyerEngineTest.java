package com.example.challenges.airline;

import com.example.challenges.airline.domain.FlightBatchRequestDto;
import com.example.challenges.airline.domain.LoyaltyReport;
import com.example.challenges.airline.enumeration.CabinClass;
import com.example.challenges.airline.enumeration.Tier;
import com.example.challenges.airline.strategy.CabinClassStrategy;
import com.example.challenges.airline.strategy.TierStrategy;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class FrequentFlyerEngineTest {

    private final ChallengeTestRunner<FlightBatchRequestDto, LoyaltyReport> runner = new ChallengeTestRunner<>(
            "airline",
            FlightBatchRequestDto.class,
            LoyaltyReport.class,
            input -> new FrequentFlyerEngine(
                    Arrays.stream(CabinClass.values()).map(CabinClassStrategy::new).toList(),
                    Arrays.stream(Tier.values()).map(TierStrategy::new).toList()).processFlights(input)
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