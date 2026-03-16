package com.example.challenges.logs.domain;

import com.example.challenges.logs.enumeration.FailureLevels;

public record LogEntry(String module, String level, String message) {
    public boolean isValid() {
        return FailureLevels.hasFailed(level) &&
                module != null &&
                message != null &&
                !message.trim().isEmpty();
    }
}
