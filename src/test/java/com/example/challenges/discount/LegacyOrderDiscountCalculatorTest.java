package com.example.challenges.discount;

import com.example.challenges.discount.domain.Item;
import com.example.challenges.discount.domain.Order;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class LegacyOrderDiscountCalculatorTest {

    private final ChallengeTestRunner<Order, BigDecimal> runner = new ChallengeTestRunner<>(
            "discount_calculator",
            Order.class,
            BigDecimal.class,
            input -> new LegacyOrderDiscountCalculator(
                    List.of(new TierDiscountRule("GOLD", "0.10"),
                            new TierDiscountRule("SILVER", "0.05"),
                            new WelcomeDiscountRule())
            ).calculateDiscount(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_empty_order",
                "02_gold_tier_discount",
                "03_silver_tier_discount",
                "04_welcome_promo_applied",
                "05_welcome_promo_rejected",
                "06_welcome_promo_quantity_bug",
                "07_gold_tier_quantity_bug",
                "08_silver_tier_quantity_bug"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }

    @Test
    void shouldThrowException_WhenItemPriceIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Item("Laptop", new BigDecimal("-100.00"), 1)
        );

        Assertions.assertEquals("Price is not valid", exception.getMessage());
    }
}