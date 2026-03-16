package com.example.challenges.iam.domain;

import com.example.challenges.iam.enumeration.Effect;

import java.util.Set;

public record PolicyRule(String role, String action, String effect) {
    public boolean isValid() {
        return role != null &&
                action != null &&
                Effect.isValidEffect(effect);
    }

    public boolean appliesTo(Set<String> userRoles, String targetAction) {
        return userRoles.contains(role) && targetAction.equals(action);
    }

    public boolean isAllow() {
        return Effect.ALLOW.name().equals(effect);
    }

    public boolean isDeny() {
        return Effect.DENY.name().equals(effect);
    }
}