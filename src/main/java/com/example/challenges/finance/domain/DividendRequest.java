package com.example.challenges.finance.domain;

import java.util.List;

public record DividendRequest(List<InvestorPosition> positions, List<DividendEvent> events) {}
