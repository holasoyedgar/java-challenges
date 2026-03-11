package com.example.challenges.analytics;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NeighborhoodAnalyzer {

    public record Listing(String id, String neighborhood, BigDecimal price, boolean isActive) {}
    public record NeighborhoodStats(String neighborhood, long activeCount, BigDecimal topPrice) {}

    public List<NeighborhoodStats> analyze(List<Listing> listings) {
        return listings.stream()
                .filter(Listing::isActive)
                .collect(Collectors.groupingBy(Listing::neighborhood))
                .values()
                .stream()
                .map(NeighborhoodAnalyzer::processListings)
                .sorted(Comparator.comparing(NeighborhoodStats::neighborhood))
                .toList();
    }

    private static NeighborhoodStats processListings(List<Listing> listings) {
        String neighborhood = listings.get(0).neighborhood();
        int activeCount = listings.size();
        BigDecimal topPrice = getNeighborhoodTopPrice(listings);
        return new NeighborhoodStats(neighborhood, activeCount, topPrice);
    }

    private static BigDecimal getNeighborhoodTopPrice(List<Listing> listings) {
        return listings.stream()
                .map(Listing::price)
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(null);
    }
}