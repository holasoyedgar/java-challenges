package com.example.challenges.iam.domain;

import java.util.List;

public record UserContext(String username, List<String> roles) {
    public boolean isValid() {
        return username != null &&
                !username.isBlank() &&
                roles != null &&
                !roles.isEmpty();
    }
}