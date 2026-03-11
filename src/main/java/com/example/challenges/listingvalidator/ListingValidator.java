package com.example.challenges.listingvalidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListingValidator {

    public record RawListing(String id, BigDecimal price, String neighborhood, Integer bedrooms) {}
    public record ValidationReport(List<RawListing> validListings, Map<String, List<String>> errorMap) {}
    private record ValidationResult(RawListing rawListing, List<String> errors) {}

    enum ValidationError {
        NEGATIVE_PRICE,
        MISSING_NEIGHBORHOOD,
        NEGATIVE_BEDROOMS
    }

    public ValidationReport validate(List<RawListing> listings) {
        Map<Boolean, List<ValidationResult>> map = listings.stream()
                .map(rawListing -> new ValidationResult(rawListing, calculateErrorPerListing(rawListing)))
                .collect(Collectors.partitioningBy(validationResult -> validationResult.errors().isEmpty()));

        List<RawListing> validListings = map.get(true)
                .stream()
                .map(ValidationResult::rawListing)
                .toList();

        Map<String, List<String>> errorMap = map.get(false)
                .stream()
                .collect(Collectors.toMap(
                        validationResult -> validationResult.rawListing().id(),
                        ValidationResult::errors
                ));
        return new ValidationReport(validListings, errorMap);
    }

    private List<String> calculateErrorPerListing(RawListing rawListing) {
        List<String> errors = new ArrayList<>();
        if (rawListing.price() != null && rawListing.price().compareTo(BigDecimal.ZERO) < 0) {
            errors.add(ValidationError.NEGATIVE_PRICE.toString());
        }
        if (rawListing.neighborhood() == null || rawListing.neighborhood().isBlank()) {
            errors.add(ValidationError.MISSING_NEIGHBORHOOD.toString());
        }
        if (rawListing.bedrooms() != null && rawListing.bedrooms() < 0) {
            errors.add(ValidationError.NEGATIVE_BEDROOMS.toString());
        }
        return errors;
    }
}