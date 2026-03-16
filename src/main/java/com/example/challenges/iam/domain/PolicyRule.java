package com.example.challenges.iam.domain;

import com.example.challenges.iam.enumeration.Effect;

public record PolicyRule(String role, String action, String effect) {
    public boolean isValid() {
        return role != null &&
                action != null &&
                Effect.isValidEffect(effect);
    }
}