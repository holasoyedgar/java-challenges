package com.example.challenges.listingvalidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListingValidator {

    public record RawListing(String id, BigDecimal price, String neighborhood, Integer bedrooms) {}
    public record ValidationReport(List<RawListing> validListings, Map<String, List<String>> errorMap) {}
    enum ValidationError {
        NEGATIVE_PRICE,
        MISSING_NEIGHBORHOOD,
        NEGATIVE_BEDROOMS
    }

    public ValidationReport validate(List<RawListing> listings) {
        List<RawListing> validListings = new ArrayList<>();
        Map<String, List<String>> errorMap = new HashMap<>();
        for (RawListing rawListing : listings) {
            List<String> errors = new ArrayList<>();
            if (rawListing.price != null && rawListing.price.compareTo(BigDecimal.ZERO) < 0) {
                errors.add(ValidationError.NEGATIVE_PRICE.toString());
            }
            if (rawListing.neighborhood == null || rawListing.neighborhood.isBlank()) {
                errors.add(ValidationError.MISSING_NEIGHBORHOOD.toString());
            }
            if (rawListing.bedrooms != null && rawListing.bedrooms < 0) {
                errors.add(ValidationError.NEGATIVE_BEDROOMS.toString());
            }
            if (!errors.isEmpty()) {
                errorMap.put(rawListing.id, errors);
            } else {
                validListings.add(rawListing);
            }
        }
        return new ValidationReport(validListings, errorMap);
    }
}