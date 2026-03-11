package com.example.challenges.listingvalidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ListingValidator {

    public record RawListing(String id, BigDecimal price, String neighborhood, Integer bedrooms) {}
    public record ValidationReport(List<RawListing> validListings, Map<String, List<String>> errorMap) {}

    public ValidationReport validate(List<RawListing> listings) {
        // Tu código aquí
        return null;
    }
}