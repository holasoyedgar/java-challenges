package com.example.challenges.airline.enumeration;

public enum CabinClass {
    ECONOMY(1),
    BUSINESS(1.5),
    FIRST(2);

    private final double rate;

    CabinClass(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
