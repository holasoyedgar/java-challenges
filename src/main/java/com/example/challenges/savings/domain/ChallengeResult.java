package com.example.challenges.savings.domain;

import java.math.BigDecimal;

public record ChallengeResult(BigDecimal totalSaved, int successfulDays, int longestStreak) {}
