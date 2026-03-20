package com.example.challenges.airline.domain;

import java.util.List;

public record FlightBatchRequestDto(List<FlightRecordDto> flights) {
    public FlightBatchRequestDto {
        if (flights == null) {
            throw new IllegalArgumentException("Payload cannot be null");
        }
        flights = flights.stream().toList();
    }
}