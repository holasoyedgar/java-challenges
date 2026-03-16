package com.example.challenges.iam;

import com.example.challenges.iam.domain.AccessRequest;
import com.example.challenges.iam.domain.AccessResult;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PolicyEvaluatorTest {

    private final ChallengeTestRunner<AccessRequest, AccessResult> runner = new ChallengeTestRunner<>(
            "policy_evaluator",
            AccessRequest.class,
            AccessResult.class,
            input -> new PolicyEvaluator().evaluateAccess(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_explicit_allow",
                "02_explicit_deny_overrides_allow",
                "03_default_deny_no_rules_match",
                "04_unassigned_role_allow_ignored",
                "05_empty_policies",
                "06_null_collections_in_request",
                "07_user_without_roles",
                "08_multiple_allows_overridden_by_single_deny",
                "09_malformed_policy_with_nulls",
                "10_partial_action_match_rejected"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}