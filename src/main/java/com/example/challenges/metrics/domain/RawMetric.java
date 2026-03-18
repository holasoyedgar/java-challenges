package com.example.challenges.metrics.domain;

import com.example.challenges.metrics.enumeration.Unit;

public record RawMetric(String name, String unit, Double value) {
    public boolean isValid() {
        return name != null && unit != null && value != null;
    }

    public boolean isUnitProcessable() {
        return isUnitPercent() || isUnitCount();
    }

    private boolean isUnitPercent() {
        return Unit.PERCENT.name().equals(unit);
    }

    private boolean isUnitCount() {
        return Unit.COUNT.name().equals(unit);
    }
}
