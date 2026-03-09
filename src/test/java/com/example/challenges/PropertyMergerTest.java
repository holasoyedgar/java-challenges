package com.example.challenges;

import com.example.util.ChallengeTestRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class PropertyMergerTest {

    record PropertyMergerWrapper(List<MlsListing> listings) {}

    private final ChallengeTestRunner<PropertyMergerWrapper, MergedProperty[]> runner = new ChallengeTestRunner<>(
            "property_merger",
            PropertyMergerWrapper.class,
            MergedProperty[].class,
            input -> new PropertyMerger()
                    .mergeListings(input.listings())
                    .toArray(MergedProperty[]::new)
    );

    static Stream<String> testCaseProvider() {
        return Stream.of(
                "01_single_property_coalesce",
                "02_multiple_properties_sorting",
                "03_edge_case_latest_price_null",
                "04_edge_case_never_provided_fields",
                "05_complex_unordered_updates"
        );
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    void executeTests(String caseName) {
        runner.runTest(caseName);
    }
}