package com.example.challenges.airline;

import com.example.challenges.airline.domain.FlightBatchRequestDto;
import com.example.challenges.airline.domain.FlightRecord;
import com.example.challenges.airline.domain.LoyaltyReport;
import com.example.challenges.airline.domain.PassengerSummary;
import com.example.challenges.airline.enumeration.Tier;
import com.example.challenges.airline.mapper.FlightRecordMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FrequentFlyerEngine {
    public LoyaltyReport processFlights(FlightBatchRequestDto request) {
        if (request == null || request.flights().isEmpty()) {
            return new LoyaltyReport(List.of());
        }

        Comparator<PassengerSummary> passengerSummaryComparator = Comparator.comparing(PassengerSummary::totalMiles)
                .reversed()
                .thenComparing(PassengerSummary::passengerId);

        List<PassengerSummary> passengerSummaries = request.flights()
                .stream()
                .map(FlightRecordMapper::toDomain)
                .filter(record -> record != null && record.isValid())
                .collect(Collectors.toMap(FlightRecord::passengerId, record -> record.cabinClass().calculateQualifyingMiles(record.distance()), Integer::sum))
                .entrySet().stream()
                .map(entry -> new PassengerSummary(entry.getKey(), entry.getValue(), Tier.fromMiles(entry.getValue())))
                .sorted(passengerSummaryComparator)
                .toList();

        return new LoyaltyReport(passengerSummaries);
    }
}