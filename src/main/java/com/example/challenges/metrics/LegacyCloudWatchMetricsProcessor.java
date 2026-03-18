package com.example.challenges.metrics;

import com.example.challenges.metrics.domain.AggregatedMetric;
import com.example.challenges.metrics.domain.MetricReportRequest;
import com.example.challenges.metrics.domain.MetricReportResult;
import com.example.challenges.metrics.domain.RawMetric;

import java.util.List;
import java.util.stream.Collectors;

public class LegacyCloudWatchMetricsProcessor {

    public MetricReportResult processMetrics(MetricReportRequest request) {
        if (request == null || !request.isValid()) {
            return new MetricReportResult(List.of());
        }

        List<AggregatedMetric> results = request.metrics()
                .stream()
                .filter(metric -> {
                    if (metric == null || !metric.isValid()) {
                        // Inform about corrupted data.
                        System.out.println("Alert: A null or malformed metric was discarded");
                        return false;
                    }
                    return true;
                })
                .filter(RawMetric::isUnitProcessable)
                .collect(Collectors.groupingBy(
                        RawMetric::name,
                        Collectors.averagingDouble(RawMetric::value)))
                .entrySet()
                .stream()
                .map(entry -> new AggregatedMetric(entry.getKey(), entry.getValue()))
                .toList();

        return new MetricReportResult(results);
    }

}