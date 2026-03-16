package com.example.challenges.savings;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SavingsChallengeTrackerTest {

    private final ChallengeTestRunner<ChallengeRequest, ChallengeResult> runner = new ChallengeTestRunner<>(
            "savings_challenge",
            ChallengeRequest.class,
            ChallengeResult.class,
            input -> new SavingsChallengeTracker().evaluateChallenge(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_perfect_streak",
                "02_broken_streak_middle",
                "03_insufficient_deposit",
                "04_unsorted_days",
                "05_empty_challenge"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}