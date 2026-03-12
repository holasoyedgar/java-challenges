package com.example.challenges.listingvalidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ListingValidator {

    public record RawListing(String id, BigDecimal price, String neighborhood, Integer bedrooms) {}
    public record ValidationReport(List<RawListing> validListings, Map<String, List<String>> errorMap) {}
    private record ValidationResult(RawListing rawListing, List<String> errors) {}

    private final List<ValidationRule> rules;

    public ListingValidator(List<ValidationRule> rules) {
        this.rules = rules;
    }

    public ValidationReport validate(List<RawListing> listings) {
        Map<Boolean, List<ValidationResult>> map = listings.stream()
                .map(rawListing -> new ValidationResult(rawListing, calculateErrors(rawListing)))
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

    private List<String> calculateErrors(RawListing rawListing) {
        return rules.stream()
                .map(rule -> rule.evaluate(rawListing))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
}

interface ValidationRule {
    Optional<String> evaluate(ListingValidator.RawListing rawListing);
}

class NegativePriceRule implements ValidationRule {

    @Override
    public Optional<String> evaluate(ListingValidator.RawListing rawListing) {
        if (rawListing.price() != null && rawListing.price().compareTo(BigDecimal.ZERO) < 0) {
            return Optional.of("NEGATIVE_PRICE");
        }
        return Optional.empty();
    }
}

class MissingNeighborhoodRule implements ValidationRule {

    @Override
    public Optional<String> evaluate(ListingValidator.RawListing rawListing) {
        if (rawListing.neighborhood() == null || rawListing.neighborhood().isBlank()) {
            return Optional.of("MISSING_NEIGHBORHOOD");
        }
        return Optional.empty();
    }
}

class NegativeBedroomsRule implements ValidationRule {

    @Override
    public Optional<String> evaluate(ListingValidator.RawListing rawListing) {
        if (rawListing.bedrooms() != null && rawListing.bedrooms() < 0) {
            return Optional.of("NEGATIVE_BEDROOMS");
        }
        return Optional.empty();
    }
}