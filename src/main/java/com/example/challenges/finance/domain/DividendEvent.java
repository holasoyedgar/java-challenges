package com.example.challenges.finance.domain;

import java.math.BigDecimal;

public record DividendEvent(String ticker, BigDecimal dividendPerShare, String exDividendDate) {}
