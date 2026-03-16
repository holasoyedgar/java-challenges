package com.example.challenges.logs.domain;

import java.util.List;
import java.util.Objects;

public record LogWrapper(List<LogEntry> logs) {
    public LogWrapper {
        logs = logs != null ? logs.stream().filter(Objects::nonNull).toList() : List.of();
    }
}
