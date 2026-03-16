package com.example.challenges.iam.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public record AccessRequest(UserContext userContext, List<PolicyRule> policies, String targetAction) {
    public AccessRequest {
        policies = policies != null ? policies.stream().filter(Objects::nonNull).filter(PolicyRule::isValid).toList() : List.of();
    }

    public boolean isValid() {
        return userContext != null &&
                userContext.isValid() &&
                targetAction != null;
    }

    public Set<String> getUserContextRoles() {
        return new HashSet<>(userContext.roles());
    }
}