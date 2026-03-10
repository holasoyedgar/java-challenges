package com.example.challenges.orderprocessor;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class OrderProcessorTest {

    // 1. El Wrapper para los dos parámetros de entrada
    record OrderInputWrapper(List<Order> orders, List<String> badIds) {}

    // 2. El Runner espera un Array de Strings como salida
    private final ChallengeTestRunner<OrderInputWrapper, String[]> runner = new ChallengeTestRunner<>(
            "order_processor",
            OrderInputWrapper.class,
            String[].class,
            
            // Ejecutamos tu método y lo convertimos a un Array puro para la aserción
            input -> new OrderProcessor()
                    .process(input.orders(), input.badIds())
                    .toArray(String[]::new)
    );

    // 3. Suite de pruebas exhaustiva
    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_comprehensive_flow",
                "02_boundary_discounts",
                "03_bad_ids_and_negatives",
                "04_unrecognized_types"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}