package com.example.challenges.listingvalidator;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ListingValidatorTest {

    record ValidatorWrapper(List<ListingValidator.RawListing> listings) {}

    private final ChallengeTestRunner<ValidatorWrapper, ListingValidator.ValidationReport> runner = new ChallengeTestRunner<>(
            "listing_validator",
            ValidatorWrapper.class,
            ListingValidator.ValidationReport.class,
            input -> new ListingValidator().validate(input.listings())
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_all_valid",
                "02_single_errors",
                "03_multiple_errors_accumulation"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}