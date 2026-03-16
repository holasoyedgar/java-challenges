package com.example.challenges.savings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class SavingsChallengeTracker {
    public ChallengeResult evaluateChallenge(ChallengeRequest request) {
        List<DailyDeposit> orderedDeposits = request.deposits()
                .stream()
                .sorted(Comparator.comparing(DailyDeposit::dayOfYear))
                .toList();
        BigDecimal totalSaved = BigDecimal.ZERO;
        int successfulDays = 0;
        int longestStreak = 0;
        int currentStreak = 0;
        int lastDay = 0;

        for (DailyDeposit deposit : orderedDeposits) {
            if (deposit.isSuccessfulDay()) {
                successfulDays++;
                if (deposit.isDayConsecutive(lastDay)) {
                    currentStreak++;
                    longestStreak = Math.max(longestStreak, currentStreak);
                } else {
                    currentStreak = 1;
                }
            } else {
                currentStreak = 0;
            }
            totalSaved = totalSaved.add(deposit.amount());
            lastDay = deposit.dayOfYear();
        }
        return new ChallengeResult(totalSaved.setScale(2, RoundingMode.HALF_UP), successfulDays, longestStreak);
    }
}