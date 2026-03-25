package com.example.challenges.sofipo.domain;

import java.math.BigDecimal;

public record InvestmentRequest(
        BigDecimal principal,
        int termDays
) {}