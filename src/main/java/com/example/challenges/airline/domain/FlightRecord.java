package com.example.challenges.airline.domain;

import com.example.challenges.airline.enumeration.CabinClass;

public record FlightRecord(String passengerId, String flightNumber, Integer distance, CabinClass cabinClass) {
    public boolean isValid() {
        return passengerId != null &&
                flightNumber != null &&
                distance != null &&
                distance > 0 &&
                cabinClass != null;
    }
}