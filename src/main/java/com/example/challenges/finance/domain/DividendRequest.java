package com.example.challenges.finance.domain;

import java.util.List;

public record DividendRequest(List<InvestorPosition> positions, List<DividendEvent> events) {
    public boolean isComplete() {
        return hasPositions() && hasEvents();
    }

    public boolean hasPositions() {
        return positions != null && !positions.isEmpty();
    }

    public boolean hasEvents() {
        return events != null && !events.isEmpty();
    }
}
