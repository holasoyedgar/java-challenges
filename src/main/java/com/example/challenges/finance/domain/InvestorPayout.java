package com.example.challenges.finance.domain;

import java.math.BigDecimal;

public record InvestorPayout(String investorId, BigDecimal totalPayout) {}
