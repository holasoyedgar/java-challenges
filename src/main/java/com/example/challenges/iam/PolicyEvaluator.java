package com.example.challenges.iam;

import com.example.challenges.iam.domain.AccessRequest;
import com.example.challenges.iam.domain.AccessResult;
import com.example.challenges.iam.domain.PolicyRule;
import com.example.challenges.iam.enumeration.Reason;

import java.util.List;
import java.util.Set;

public class PolicyEvaluator {

    public AccessResult evaluateAccess(AccessRequest request) {
        if (request == null || !request.isValid()) {
            return new AccessResult(false, Reason.DEFAULT_DENY.name());
        }
        Set<String> userRoles = request.getUserContextRoles();
        userRoles.add("MYYYY");
        System.out.println(userRoles);
        List<PolicyRule> applicableRules = request.policies().stream()
                .filter(rule -> rule.appliesTo(userRoles, request.targetAction()))
                .toList();

        if (applicableRules.stream().anyMatch(PolicyRule::isDeny)) {
            return new AccessResult(false, Reason.EXPLICIT_DENY.name());
        }

        if (applicableRules.stream().anyMatch(PolicyRule::isAllow)) {
            return new AccessResult(true, Reason.EXPLICIT_ALLOW.name());
        }

        return new AccessResult(false, Reason.DEFAULT_DENY.name());
    }
}