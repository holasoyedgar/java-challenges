package com.example.challenges.airline.domain;

import com.example.challenges.airline.enumeration.Tier;

public record PassengerSummary(String passengerId, int totalMiles, Tier tierStatus) {}