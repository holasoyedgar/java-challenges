package com.example.challenges.searchengine;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchEngine {

    public record Listing(String id,
                   boolean isActive,
                   BigDecimal price,
                   String agentId) {
        public boolean hasValidState() {
            return id != null &&
                    price != null &&
                    agentId != null &&
                    price.compareTo(BigDecimal.ZERO) > 0;
        }
    }

    public List<Listing> getTopListings(List<Listing> listings, int k) {
        if (k < 1 || listings == null) {
            throw new IllegalArgumentException("The arguments are invalid!");
        }

        Comparator<Listing> naturalOrder = Comparator.comparing(Listing::price)
                .thenComparing(Listing::id);

        Collection<Listing> cheapestPerAgent = listings.stream()
                .filter(Objects::nonNull)
                .filter(Listing::hasValidState)
                .filter(Listing::isActive)
                .collect(Collectors.toMap(
                        Listing::agentId,
                        Function.identity(),
                        BinaryOperator.minBy(naturalOrder)
                )).values();

        PriorityQueue<Listing> priorityQueue = new PriorityQueue<>(naturalOrder.reversed());

        for (Listing listing : cheapestPerAgent) {
            priorityQueue.offer(listing);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.stream().sorted(naturalOrder).toList();
    }
}