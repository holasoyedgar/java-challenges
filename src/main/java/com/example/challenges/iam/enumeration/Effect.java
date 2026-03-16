package com.example.challenges.iam.enumeration;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Effect {
    ALLOW,
    DENY;

    private static final Set<String> VALID_EFFECTS = Arrays.stream(values())
            .map(Enum::name)
            .collect(Collectors.toUnmodifiableSet());

    public static boolean isValidEffect(String effect) {
        return effect != null && VALID_EFFECTS.contains(effect);
    }
}
