package com.example.challenges.metrics.domain;

import java.util.List;

public record MetricReportRequest(List<RawMetric> metrics) {
    public boolean isValid() {
        return metrics != null && !metrics.isEmpty();
    }
}
