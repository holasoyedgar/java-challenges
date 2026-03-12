package com.example.challenges.viewtracker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class ViewTracker {

    private final Map<String, LongAdder> listingViewCounter = new ConcurrentHashMap<>();

    public void recordView(String listingId) {
        listingViewCounter.computeIfAbsent(listingId, k -> new LongAdder()).increment();
    }

    public int getViews(String listingId) {
        LongAdder views = listingViewCounter.get(listingId);
        return views == null ? 0 : views.intValue();
    }
}