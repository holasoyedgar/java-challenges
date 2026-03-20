package com.example.challenges.airline;

import com.example.challenges.airline.domain.FlightBatchRequestDto;
import com.example.challenges.airline.domain.FlightRecord;
import com.example.challenges.airline.domain.LoyaltyReport;
import com.example.challenges.airline.domain.PassengerSummary;
import com.example.challenges.airline.enumeration.Tier;
import com.example.challenges.airline.mapper.FlightRecordMapper;
import com.example.challenges.airline.strategy.CabinClassStrategy;
import com.example.challenges.airline.strategy.TierStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FrequentFlyerEngine {

    private final List<CabinClassStrategy> cabinClassStrategies;
    private final List<TierStrategy> tierStrategies;

    public FrequentFlyerEngine(List<CabinClassStrategy> cabinClassStrategies, List<TierStrategy> tierStrategies) {
        this.cabinClassStrategies = cabinClassStrategies;
        this.tierStrategies = tierStrategies;
    }

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
                .collect(Collectors.toMap(FlightRecord::passengerId, this::calculateQualifyingMiles, Integer::sum))
                .entrySet().stream()
                .map(entry -> new PassengerSummary(entry.getKey(), entry.getValue(), this.determineTier(entry.getValue())))
                .sorted(passengerSummaryComparator)
                .toList();

        return new LoyaltyReport(passengerSummaries);
    }

    private Integer calculateQualifyingMiles(FlightRecord flightRecord) {
        return cabinClassStrategies.stream()
                .filter(cabinClassStrategy -> cabinClassStrategy.isApplicable(flightRecord.cabinClass()))
                .findFirst()
                .map(cabinClassStrategy -> cabinClassStrategy.calculateQualifyingMiles(flightRecord.distance()))
                .orElseThrow();
    }

    private Tier determineTier(int qualifyingMiles) {
        return tierStrategies.stream()
                .map(tierStrategy -> tierStrategy.findApplicableTier(qualifyingMiles))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow();
    }
}