package com.example.challenges.finance.domain;

import java.util.List;

public record DividendPayoutReport(List<InvestorPayout> payouts) {}
