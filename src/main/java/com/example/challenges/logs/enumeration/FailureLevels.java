package com.example.challenges.logs.enumeration;

import java.util.HashSet;
import java.util.Set;

public enum FailureLevels {
    ERROR, FATAL;

    private static final Set<String> FAILURE_LEVELS = new HashSet<>();

    static {
        for (FailureLevels failureLevel : values()) {
            FAILURE_LEVELS.add(failureLevel.toString());
        }
    }

    public static boolean hasFailed(String level) {
        return FAILURE_LEVELS.contains(level);
    }
}