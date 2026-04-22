package com.example.challenges.trading;

import com.example.challenges.trading.domain.MatchingResult;
import com.example.challenges.trading.domain.OrderBookRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LimitOrderBookEngineTest {

    private final ChallengeTestRunner<OrderBookRequest, MatchingResult> runner = new ChallengeTestRunner<>(
            "order_book",
            OrderBookRequest.class,
            MatchingResult.class,
            input -> new LimitOrderBookEngine().processOrders(input)
    );

    static Stream<String> testCaseProvider() {
        return ChallengeTestRunner.getTestCases("order_book");
    }

    @ParameterizedTest(name = "Test Case: {0}")
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}