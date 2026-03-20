package com.example.challenges.airline.mapper;

import com.example.challenges.airline.domain.FlightRecord;
import com.example.challenges.airline.domain.FlightRecordDto;
import com.example.challenges.airline.enumeration.CabinClass;

public class FlightRecordMapper {
    public static FlightRecord toDomain(FlightRecordDto flightRecordDto) {
        if (flightRecordDto == null) {
            return null;
        }

        CabinClass cabinClass = null;

        try {
            cabinClass = CabinClass.valueOf(flightRecordDto.cabinClass());
        } catch (IllegalArgumentException e) {
            // Invalid cabinClass. Ignoring the exception and return the record with null cabinClass.
        }

        return new FlightRecord(flightRecordDto.passengerId(),
                flightRecordDto.flightNumber(),
                flightRecordDto.distance(),
                cabinClass);
    }
}
