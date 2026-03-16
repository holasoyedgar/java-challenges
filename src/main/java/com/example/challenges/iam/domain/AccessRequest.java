package com.example.challenges.iam.domain;

import java.util.List;

public record AccessRequest(UserContext userContext, List<PolicyRule> policies, String targetAction) {}
