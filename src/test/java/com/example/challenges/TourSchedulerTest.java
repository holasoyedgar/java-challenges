package com.example.challenges;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

class TourSchedulerTest {

    record TourInputWrapper(List<Tour> existingTours, Tour request, int maxSimultaneous) {}

    private final ChallengeTestRunner<TourInputWrapper, Boolean> runner = new ChallengeTestRunner<>(
            "tour_scheduler",
            TourInputWrapper.class,
            Boolean.class,
            input -> new TourScheduler().canBookTour(input.existingTours(), input.request(), input.maxSimultaneous())
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_happy_path_fits",
                "02_unhappy_path_exceeds_limit"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}