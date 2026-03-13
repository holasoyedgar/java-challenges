package com.example.challenges.inventory;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InventoryManagerTest {

    private final ChallengeTestRunner<InventoryInput, ReservationResult> runner = new ChallengeTestRunner<>(
            "inventory_manager",
            InventoryInput.class,
            ReservationResult.class,
            input -> new InventoryManager().processReservations(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_all_successful",
                "02_insufficient_stock",
                "03_item_not_found",
                "04_mixed_requests_sequential_exhaustion",
                "05_empty_requests"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}