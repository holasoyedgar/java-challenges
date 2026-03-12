package com.example.challenges.listingvalidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListingValidator {

    public record RawListing(String id, BigDecimal price, String neighborhood, Integer bedrooms) {}
    public record ValidationReport(List<RawListing> validListings, Map<String, List<String>> errorMap) {}
    private record ValidationResult(RawListing rawListing, List<String> errors) {}

    enum ValidationError {
        NEGATIVE_PRICE(rawListing -> rawListing.price() != null && rawListing.price().compareTo(BigDecimal.ZERO) < 0),
        MISSING_NEIGHBORHOOD(rawListing -> rawListing.neighborhood() == null || rawListing.neighborhood().isBlank()),
        NEGATIVE_BEDROOMS(rawListing -> rawListing.bedrooms() != null && rawListing.bedrooms() < 0);

        private final Predicate<RawListing> predicate;
        ValidationError(Predicate<RawListing> predicate) {
            this.predicate = predicate;
        }

        public static List<String> getErrors(RawListing rawListing) {
            List<String> errors = new ArrayList<>();
            for (ValidationError error : values()) {
                if (error.predicate.test(rawListing)) {
                    errors.add(error.toString());
                }
            }
            return errors;
        }
    }

    public ValidationReport validate(List<RawListing> listings) {
        Map<Boolean, List<ValidationResult>> map = listings.stream()
                .map(rawListing -> new ValidationResult(rawListing, ValidationError.getErrors(rawListing)))
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

}