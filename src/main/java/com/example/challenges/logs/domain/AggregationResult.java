package com.example.challenges.logs.domain;

import java.util.Map;

public record AggregationResult(Map<String, Long> errorCounts) {}
