package com.example.challenges.viewtracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewTracker {

    private final Map<String, Integer> listingViewCounter = new ConcurrentHashMap<>();

    public void recordView(String listingId) {
        listingViewCounter.merge(listingId, 1, Integer::sum);
    }

    public int getViews(String listingId) {
        return listingViewCounter.getOrDefault(listingId, 0);
    }
}