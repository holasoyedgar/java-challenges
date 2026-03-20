package com.example.challenges.airline.strategy;

import com.example.challenges.airline.enumeration.CabinClass;

public class CabinClassStrategy {
    private final CabinClass cabinClass;

    public CabinClassStrategy(CabinClass cabinClass) {
        this.cabinClass = cabinClass;
    }

    public int calculateQualifyingMiles(Integer miles) {
        return (int) (miles * cabinClass.getRate());
    }

    public boolean isApplicable(CabinClass flightRecordCabinClass) {
        return cabinClass.equals(flightRecordCabinClass);
    }
}
