package com.example.challenges.football.enumeration;

public enum Result {
    WIN(3),
    DRAW(1),
    LOSE(0);

    private final int points;

    Result(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
