package com.example.challenges.airline.strategy;

import com.example.challenges.airline.enumeration.Tier;

import java.util.Optional;

public class TierStrategy {
    private final Tier tier;

    public TierStrategy(Tier tier) {
        this.tier = tier;
    }

    public Optional<Tier> findApplicableTier(int qualifyingMiles) {
        if (qualifyingMiles >= tier.getMinimumRange() && qualifyingMiles <= tier.getMaximumRange()) {
            return Optional.of(tier);
        }

        return Optional.empty();
    }
}
