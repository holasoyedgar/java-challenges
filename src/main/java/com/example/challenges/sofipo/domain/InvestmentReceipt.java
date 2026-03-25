package com.example.challenges.sofipo.domain;

import java.math.BigDecimal;

public record InvestmentReceipt(
        BigDecimal principal,
        BigDecimal grossYield,
        BigDecimal taxRetained,
        BigDecimal netYield
) {}