package com.example.challenges.metrics;

import com.example.challenges.metrics.domain.AggregatedMetric;
import com.example.challenges.metrics.domain.MetricReportRequest;
import com.example.challenges.metrics.domain.MetricReportResult;
import com.example.challenges.metrics.domain.RawMetric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LegacyCloudWatchMetricsProcessor {

    public MetricReportResult processMetrics(MetricReportRequest request) {
        if (request == null || request.metrics() == null) {
            return new MetricReportResult(new ArrayList<>());
        }

        Map<String, List<Double>> groupedMetrics = new HashMap<>();

        for (int i = 0; i < request.metrics().size(); i++) {
            RawMetric metric = request.metrics().get(i);
            if (metric != null && metric.name() != null && metric.unit() != null && metric.value() != null) {
                if (metric.unit().equals("PERCENT") || metric.unit().equals("COUNT")) {
                    if (!groupedMetrics.containsKey(metric.name())) {
                        groupedMetrics.put(metric.name(), new ArrayList<>());
                    }
                    groupedMetrics.get(metric.name()).add(metric.value());
                }
            }
        }

        List<AggregatedMetric> results = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : groupedMetrics.entrySet()) {
            double sum = 0;
            for (Double val : entry.getValue()) {
                sum += val;
            }
            double average = sum / entry.getValue().size();

            String status = "OK";
            if (average >= 80.0) {
                status = "ALARM";
            }

            results.add(new AggregatedMetric(entry.getKey(), average, status));
        }

        return new MetricReportResult(results);
    }
}