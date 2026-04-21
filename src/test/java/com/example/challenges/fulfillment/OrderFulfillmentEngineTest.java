package com.example.challenges.fulfillment;

import com.example.challenges.fulfillment.domain.FulfillmentReceipt;
import com.example.challenges.fulfillment.domain.FulfillmentRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class OrderFulfillmentEngineTest {

    private final ChallengeTestRunner<FulfillmentRequest, FulfillmentReceipt> runner = new ChallengeTestRunner<>(
            "fulfillment",
            FulfillmentRequest.class,
            FulfillmentReceipt.class,
            input -> new OrderFulfillmentEngine().processOrder(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_single_warehouse",
                "02_split_order",
                "03_partial_fulfillment",
                "04_empty_order",
                "05_complex_split"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}