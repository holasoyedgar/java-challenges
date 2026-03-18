package com.example.challenges.metrics.domain;

import java.util.List;
import java.util.Objects;

public record MetricReportRequest(List<RawMetric> metrics) {
    public MetricReportRequest {
        metrics = metrics != null ? metrics.stream().filter(Objects::nonNull).toList() : List.of();
    }
}
