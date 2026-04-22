package com.example.challenges.bom;

import com.example.challenges.bom.domain.BomReceipt;
import com.example.challenges.bom.domain.BomRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BomExplosionEngineTest {

    private final ChallengeTestRunner<BomRequest, BomReceipt> runner = new ChallengeTestRunner<>(
            "bom_explosion",
            BomRequest.class,
            BomReceipt.class,
            input -> new BomExplosionEngine().explode(input)
    );

    static Stream<String> testCaseProvider() {
        return ChallengeTestRunner.getTestCases("bom_explosion");
    }

    @ParameterizedTest(name = "Test Case: {0}")
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}