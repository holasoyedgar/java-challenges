package com.example.challenges.iam;

import com.example.challenges.iam.domain.AccessRequest;
import com.example.challenges.iam.domain.AccessResult;
import com.example.challenges.iam.domain.PolicyRule;
import com.example.challenges.iam.enumeration.Effect;
import com.example.challenges.iam.enumeration.Reason;

import java.util.Set;

public class PolicyEvaluator {

    public AccessResult evaluateAccess(AccessRequest request) {
        if (request == null || !request.isValid()) {
            return new AccessResult(false, Reason.DEFAULT_DENY.name());
        }
        Set<String> userRoles = request.getUserContextRoles();
        boolean isAllowed = false;

        for (PolicyRule rule : request.policies()) {
            if (userRoles.contains(rule.role()) && request.targetAction().equals(rule.action())) {
                if (Effect.ALLOW.name().equals(rule.effect())) {
                    isAllowed = true;
                } else {
                    return new AccessResult(false, Reason.EXPLICIT_DENY.name());
                }
            }
        }

        if (isAllowed) {
            return new AccessResult(true, Reason.EXPLICIT_ALLOW.name());
        }

        return new AccessResult(false, Reason.DEFAULT_DENY.name());
    }
}