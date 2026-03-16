package com.example.challenges.logs;

import com.example.challenges.logs.domain.AggregationResult;
import com.example.challenges.logs.domain.LogEntry;
import com.example.challenges.logs.domain.LogWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LegacyLogAggregator {

    public AggregationResult countErrorsPerModule(LogWrapper logWrapper) {
        if (logWrapper == null) {
            return new AggregationResult(new HashMap<>());
        }

        Map<String, Long> errorCounts = logWrapper.logs()
                .stream()
                .filter(LogEntry::isValid)
                .collect(Collectors.groupingBy(LogEntry::module, Collectors.counting()));

        return new AggregationResult(errorCounts);
    }
}