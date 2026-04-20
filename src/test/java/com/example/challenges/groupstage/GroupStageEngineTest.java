package com.example.challenges.groupstage;

import com.example.challenges.groupstage.domain.GroupStageReceipt;
import com.example.challenges.groupstage.domain.GroupStageRequest;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GroupStageEngineTest {

    private final ChallengeTestRunner<GroupStageRequest, GroupStageReceipt> runner = new ChallengeTestRunner<>(
            "groupstage",
            GroupStageRequest.class,
            GroupStageReceipt.class,
            input -> new GroupStageEngine().calculateStandings(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_clear_winner",
                "02_tiebreaker_goal_difference",
                "03_tiebreaker_goals_scored",
                "04_tiebreaker_alphabetical",
                "05_empty_matches"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}