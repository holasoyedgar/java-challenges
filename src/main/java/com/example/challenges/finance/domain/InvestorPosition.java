package com.example.challenges.finance.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvestorPosition(String investorId, String ticker, int shares, LocalDate purchaseDate) {
    public boolean isValid() {
        return investorId != null && ticker != null && shares > 0 && purchaseDate != null;
    }

    public boolean purchasedBefore(LocalDate exDividendDate) {
        return purchaseDate.isBefore(exDividendDate);
    }

    public BigDecimal calculatePayout(BigDecimal dividendPerShare) {
        return dividendPerShare.multiply(new BigDecimal(shares));
    }
}
