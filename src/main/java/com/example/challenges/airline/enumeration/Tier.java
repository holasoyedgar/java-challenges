package com.example.challenges.airline.enumeration;

public enum Tier {
    BRONZE(0, 9_999),
    SILVER(10_000, 49_999),
    GOLD(50_000, 99_999),
    PLATINUM(100_000, Integer.MAX_VALUE);

    private final Integer minimumRange;
    private final Integer maximumRange;

    Tier(int minimumRange, int maximumRange) {
        this.minimumRange = minimumRange;
        this.maximumRange = maximumRange;
    }

    public Integer getMinimumRange() {
        return minimumRange;
    }

    public Integer getMaximumRange() {
        return maximumRange;
    }
}
