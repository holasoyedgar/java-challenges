package com.example.challenges.billing.enumeration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Status {
    ACTIVE,
    PAST_DUE,
    CANCELED;

    private static final Set<String> VALID_STATUSES = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());

    public static boolean isValid(String status) {
        return status != null && VALID_STATUSES.contains(status);
    }
}
