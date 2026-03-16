package com.example.challenges.logs.domain;

import com.example.challenges.logs.enumeration.FailureLevel;

public record LogEntry(String module, String level, String message) {
    public boolean isError() {
        return FailureLevel.hasFailed(level) &&
                module != null &&
                message != null &&
                !message.trim().isEmpty();
    }
}
