package com.example.challenges.airline.domain;

import java.util.List;

public record FlightBatchRequest(List<FlightRecord> flights) {
    public FlightBatchRequest {
        if (flights == null) {
            throw new IllegalArgumentException("Payload cannot be null");
        }
        flights = flights.stream().toList();
    }
}