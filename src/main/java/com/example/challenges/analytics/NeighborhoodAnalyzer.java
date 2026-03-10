package com.example.challenges.analytics;

import java.math.BigDecimal;
import java.util.List;

public class NeighborhoodAnalyzer {

    public List<NeighborhoodStats> analyze(List<Listing> listings) {

        return List.of();
    }
}

record Listing(String id, String neighborhood, BigDecimal price, boolean isActive) {}
record NeighborhoodStats(String neighborhood, long activeCount, BigDecimal topPrice) {}