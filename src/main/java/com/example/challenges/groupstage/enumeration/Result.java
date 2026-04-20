package com.example.challenges.groupstage.enumeration;

public enum Result {
    WIN(3), DRAW(1), LOSE(0);
    private final int awardedPoints;

    Result(int awardedPoints) {
        this.awardedPoints = awardedPoints;
    }

    public int getAwardedPoints() {
        return awardedPoints;
    }

    public static Result fromGoals(int goalsFor, int goalsAgainst) {
        if (goalsFor > goalsAgainst) {
            return WIN;
        }
        if (goalsFor == goalsAgainst) {
            return DRAW;
        }
        return LOSE;
    }
}
