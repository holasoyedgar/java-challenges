package com.example.challenges.finance.domain;

import java.util.List;

public record DividendRequest(List<InvestorPosition> positions, List<DividendEvent> events) {
    public DividendRequest {
        if (positions == null || events == null) {
            throw new IllegalArgumentException("Malformed request, positions and/or events are null.");
        }

        positions = positions.stream().toList();
        events = events.stream().toList();
    }
}
