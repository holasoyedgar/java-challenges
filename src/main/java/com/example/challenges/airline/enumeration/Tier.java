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

    public static Tier fromMiles(int miles) {
        for (Tier tier : values()) {
            if (miles >= tier.minimumRange && miles <= tier.maximumRange) {
                return tier;
            }
        }

        throw new IllegalArgumentException("Miles out of range");
    }
}
