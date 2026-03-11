package com.example.challenges.propertymerger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyMerger {

    public record MlsListing(String zpid, LocalDateTime lastUpdated, BigDecimal price, Integer bedrooms, Integer bathrooms) {}
    public record MergedProperty(String zpid, LocalDateTime lastUpdated, BigDecimal price, Integer bedrooms, Integer bathrooms) {}

    public List<MergedProperty> mergeListings(List<MlsListing> listings) {
        return listings.stream()
                .collect(Collectors.toMap(
                        MlsListing::zpid,
                        PropertyMerger::createMergedProperty,
                        PropertyMerger::updateMergedProperty
                )).values()
                .stream()
                .sorted(Comparator.comparing(MergedProperty::zpid))
                .toList();
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

    private static MergedProperty updateMergedProperty(MergedProperty existingMergedProperty, MergedProperty newProperty) {
        BigDecimal price = existingMergedProperty.price();
        LocalDateTime lastUpdated = existingMergedProperty.lastUpdated();
        Integer bedrooms = existingMergedProperty.bedrooms();
        Integer bathrooms = existingMergedProperty.bathrooms();

        if (lastUpdated.isBefore(newProperty.lastUpdated())) {
            lastUpdated = newProperty.lastUpdated();
            price = newProperty.price();
            if (newProperty.bedrooms() != null) {
                bedrooms = newProperty.bedrooms();
            }
            if (newProperty.bathrooms() != null) {
                bathrooms = newProperty.bathrooms();
            }
        } else {
            if (bedrooms == null) {
                bedrooms = newProperty.bedrooms();
            }
            if (bathrooms == null) {
                bathrooms = newProperty.bathrooms();
            }
        }

        return new MergedProperty(
                existingMergedProperty.zpid(),
                lastUpdated,
                price,
                bedrooms,
                bathrooms
        );
    }
}