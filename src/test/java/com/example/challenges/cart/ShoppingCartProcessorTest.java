package com.example.challenges.cart;

import com.example.challenges.cart.domain.CartRequest;
import com.example.challenges.cart.domain.CartReceipt;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ShoppingCartProcessorTest {

    private final ChallengeTestRunner<CartRequest, CartReceipt> runner = new ChallengeTestRunner<>(
            "cart",
            CartRequest.class,
            CartReceipt.class,
            input -> new ShoppingCartProcessor().processCart(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_mixed_items_no_coupon",
                "02_coupon_applied",
                "03_invalid_items_skipped",
                "04_empty_request",
                "05_threshold_edge_cases"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}