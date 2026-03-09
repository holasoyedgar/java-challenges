package com.example.challenges;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyMerger {

    public List<MergedProperty> mergeListings(List<MlsListing> listings) {

        return List.of();
    }
}

record MlsListing(String zpid, LocalDateTime lastUpdated, Double price, Integer bedrooms, Integer bathrooms) {}
record MergedProperty(String zpid, LocalDateTime lastUpdated, Double price, Integer bedrooms, Integer bathrooms) {}