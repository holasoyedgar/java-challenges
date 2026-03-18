package com.example.challenges.metrics;

import com.example.challenges.metrics.domain.AggregatedMetric;
import com.example.challenges.metrics.domain.MetricReportRequest;
import com.example.challenges.metrics.domain.MetricReportResult;
import com.example.challenges.metrics.domain.RawMetric;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LegacyCloudWatchMetricsProcessor {
    private static final String OK_STATUS = "OK";
    private static final String ALARM_STATUS = "ALARM";
    private static final double ALARM_THRESHOLD = 80.0;

    public MetricReportResult processMetrics(MetricReportRequest request) {
        if (request == null || request.metrics().isEmpty()) {
            return new MetricReportResult(List.of());
        }

        List<AggregatedMetric> results = request.metrics()
                .stream()
                .filter(RawMetric::isValid)
                .filter(RawMetric::isUnitProcessable)
                .collect(Collectors.groupingBy(RawMetric::name, Collectors.averagingDouble(RawMetric::value)))
                .entrySet()
                .stream()
                .map(LegacyCloudWatchMetricsProcessor::createAggregatedMetric)
                .toList();

        return new MetricReportResult(results);
    }

    private static AggregatedMetric createAggregatedMetric(Map.Entry<String, Double> entry) {
        Double average = entry.getValue();
        String status = OK_STATUS;
        if (average >= ALARM_THRESHOLD) {
            status = ALARM_STATUS;
        }
        return new AggregatedMetric(entry.getKey(), average, status);
    }
}