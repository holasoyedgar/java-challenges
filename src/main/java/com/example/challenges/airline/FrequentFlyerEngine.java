package com.example.challenges.airline;

import com.example.challenges.airline.domain.FlightBatchRequest;
import com.example.challenges.airline.domain.LoyaltyReport;

import java.util.List;

public class FrequentFlyerEngine {

    public LoyaltyReport processFlights(FlightBatchRequest request) {
        // TODO: Implementar agregación, cálculo de multiplicadores,
        // asignación de nivel y ordenamiento.
        return new LoyaltyReport(List.of());
    }
}