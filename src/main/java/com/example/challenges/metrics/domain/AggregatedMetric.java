package com.example.challenges.metrics.domain;

import com.example.challenges.metrics.enumeration.MetricStatus;

public record AggregatedMetric(String name, Double average, String status) {
    private static final double ALARM_THRESHOLD = 80.0;

    public AggregatedMetric(String name, Double average) {
        this(name, average, determineStatus(average));
    }

    public static String determineStatus(Double average) {
        return average >= ALARM_THRESHOLD ? MetricStatus.ALARM.name() : MetricStatus.OK.name();
    }
}
