package com.example.challenges.logs;

import com.example.challenges.logs.domain.AggregationResult;
import com.example.challenges.logs.domain.LogEntry;
import com.example.challenges.logs.domain.LogWrapper;

import java.util.HashMap;
import java.util.Map;

public class LegacyLogAggregator {

    public AggregationResult countErrorsPerModule(LogWrapper input) {
        Map<String, Long> errorCounts = new HashMap<>();

        if (input != null && input.logs() != null) {
            for (int i = 0; i < input.logs().size(); i++) {
                LogEntry log = input.logs().get(i);
                if (log != null && log.level() != null && log.module() != null) {
                    if (log.level().equals("ERROR") || log.level().equals("FATAL")) {
                        if (log.message() != null && !log.message().trim().isEmpty()) {
                            String module = log.module();
                            if (errorCounts.containsKey(module)) {
                                errorCounts.put(module, errorCounts.get(module) + 1L);
                            } else {
                                errorCounts.put(module, 1L);
                            }
                        }
                    }
                }
            }
        }

        return new AggregationResult(errorCounts);
    }
}