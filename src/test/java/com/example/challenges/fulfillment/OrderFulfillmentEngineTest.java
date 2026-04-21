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
            input -> new OrderFulfillmentEngine(new ShippingPricingEngine()).processOrder(input)
    );

    static Stream<String> testCaseProvider() {
        return ChallengeTestRunner.getTestCases("fulfillment");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}