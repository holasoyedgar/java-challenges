package com.example.challenges.cloudbilling;

import com.example.challenges.cloudbilling.domain.CloudBillingReceipt;
import com.example.challenges.cloudbilling.domain.CloudBillingRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CloudBillingEngineTest {

    private final ChallengeTestRunner<CloudBillingRequest, CloudBillingReceipt> runner = new ChallengeTestRunner<>(
            "cloudbilling",
            CloudBillingRequest.class,
            CloudBillingReceipt.class,
            input -> new CloudBillingEngine().calculateMonthlyBill(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_standard_tier_normal_usage",
                "02_glacier_tier_mature_storage",
                "03_glacier_tier_early_deletion",
                "04_invalid_negative_inputs",
                "05_glacier_exact_threshold"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}