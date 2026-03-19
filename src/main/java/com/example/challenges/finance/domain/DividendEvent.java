package com.example.challenges.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendEvent(String ticker, BigDecimal dividendPerShare, LocalDate exDividendDate) {
    public boolean isValid() {
        return ticker != null && dividendPerShare != null && exDividendDate != null;
    }
}
