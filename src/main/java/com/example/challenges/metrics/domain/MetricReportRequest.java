package com.example.challenges.metrics.domain;

import java.util.List;

public record MetricReportRequest(List<RawMetric> metrics) {}
