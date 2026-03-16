package com.example.challenges.billing;

import com.example.challenges.billing.LegacySubscriptionBillingProcessor;
import com.example.challenges.billing.domain.BillingRequest;
import com.example.challenges.billing.domain.BillingResult;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class LegacySubscriptionBillingProcessorTest {

    private final ChallengeTestRunner<BillingRequest, BillingResult> runner = new ChallengeTestRunner<>(
            "subscription_billing",
            BillingRequest.class,
            BillingResult.class,
            input -> new LegacySubscriptionBillingProcessor().processBilling(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_empty_request",
                "02_single_user_active_subs",
                "03_multiple_users_mixed_status",
                "04_past_due_penalties",
                "05_malformed_data_ignored"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}