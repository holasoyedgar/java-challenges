package com.example.challenges;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PropertyMerger {


    public List<MergedProperty> mergeListings(List<MlsListing> listings) {
        List<MlsListing> orderedListings = listings.stream()
                .sorted(Comparator.comparing(MlsListing::lastUpdated).reversed())
                .toList();

        Map<String, MergedProperty> mergedPropertyMap = createMergedPropertyMap(orderedListings);

        return mergedPropertyMap
                .values()
                .stream()
                .sorted(Comparator.comparing(MergedProperty::zpid))
                .toList();
    }


    private static Map<String, MergedProperty> createMergedPropertyMap(List<MlsListing> orderedListings) {
        Map<String, MergedProperty> mergedPropertyMap = new HashMap<>();
        for (MlsListing listing : orderedListings) {
            MergedProperty newMergedProperty;
            if (mergedPropertyMap.containsKey(listing.zpid())) {
                newMergedProperty = updateMergedProperty(mergedPropertyMap.get(listing.zpid()), listing);
            } else {
                newMergedProperty = createMergedProperty(listing);

            }
            mergedPropertyMap.put(listing.zpid(), newMergedProperty);
        }
        return mergedPropertyMap;
    }


    private static MergedProperty updateMergedProperty(MergedProperty exisingMergedProperty, MlsListing listing) {
        Integer bedrooms = exisingMergedProperty.bedrooms();
        if (bedrooms == null) {
            bedrooms = listing.bedrooms();
        }
        Integer bathrooms = exisingMergedProperty.bathrooms();
        if (bathrooms == null) {
            bathrooms = listing.bathrooms();
        }

        return new MergedProperty(
                exisingMergedProperty.zpid(),
                exisingMergedProperty.lastUpdated(),
                exisingMergedProperty.price(),
                bedrooms,
                bathrooms
        );

    }


    private static MergedProperty createMergedProperty(MlsListing listing) {
        return new MergedProperty(
                listing.zpid(),
                listing.lastUpdated(),
                listing.price(),
                listing.bedrooms(),
                listing.bathrooms()
        );
    }

}


record MlsListing(String zpid, LocalDateTime lastUpdated, Double price, Integer bedrooms, Integer bathrooms) { }

record MergedProperty(String zpid, LocalDateTime lastUpdated, Double price, Integer bedrooms, Integer bathrooms) { }