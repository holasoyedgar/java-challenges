package com.example.challenges.logs.enumeration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FailureLevel {
    ERROR, FATAL;

    private static final Set<String> FAILURE_LEVELS = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    public static boolean hasFailed(String level) {
        return FAILURE_LEVELS.contains(level);
    }
}