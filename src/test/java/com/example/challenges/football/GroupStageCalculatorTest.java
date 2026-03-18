package com.example.challenges.football;

import com.example.challenges.football.domain.TournamentRequest;
import com.example.challenges.football.domain.TournamentResult;
import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class GroupStageCalculatorTest {

    private final ChallengeTestRunner<TournamentRequest, TournamentResult> runner = new ChallengeTestRunner<>(
            "group_stage",
            TournamentRequest.class,
            TournamentResult.class,
            input -> new GroupStageCalculator().calculateStandings(input)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_clear_winner",
                "02_tiebreaker_goal_difference",
                "03_tiebreaker_goals_scored",
                "04_tiebreaker_alphabetical",
                "05_empty_tournament"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}