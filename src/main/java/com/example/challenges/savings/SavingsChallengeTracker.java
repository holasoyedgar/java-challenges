package com.example.challenges.savings;

import com.example.challenges.savings.domain.ChallengeRequest;
import com.example.challenges.savings.domain.ChallengeResult;
import com.example.challenges.savings.domain.DailyDeposit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

public class SavingsChallengeTracker {
    public ChallengeResult evaluateChallenge(ChallengeRequest request) {
        if (request == null || request.deposits().isEmpty()) {
            return new ChallengeResult(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), 0, 0);
        }
        List<DailyDeposit> orderedDeposits = request.deposits()
                .stream()
                .sorted(Comparator.comparing(DailyDeposit::dayOfYear))
                .toList();
        BigDecimal totalSaved = BigDecimal.ZERO;
        int successfulDays = 0;
        int longestStreak = 0;
        int currentStreak = 0;
        int lastDay = -1;

        for (DailyDeposit deposit : orderedDeposits) {
            if (deposit.isSuccessfulDay()) {
                successfulDays++;
                if (currentStreak == 0 || deposit.isConsecutiveTo(lastDay)) {
                    currentStreak++;
                } else {
                    currentStreak = 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            } else {
                currentStreak = 0;
            }
            totalSaved = totalSaved.add(deposit.amount());
            lastDay = deposit.dayOfYear();
        }
        return new ChallengeResult(totalSaved.setScale(2, RoundingMode.HALF_UP), successfulDays, longestStreak);
    }
}