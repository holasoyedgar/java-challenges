package com.example.challenges.finance;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum HoldingPeriod {
    LONG_TERM,
    SHORT_TERM;

    private static final Set<String> HOLDING_PERIODS = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());

    public static boolean isValid(String holdingPeriod) {
        return holdingPeriod != null && HOLDING_PERIODS.contains(holdingPeriod);
    }
}
