package com.example.challenges.ocp.abstractandinterfaces.interfaces;

interface Car {
    Number calculateFuel(int tank);
    double RECOMMENDED_SPEED_FACTOR = 0.8;
    int getMaxSpeed();
    int getRecommendedSpeed();
    default void printMaxAndRecommendedSpeed() {
        System.out.println("Max speed: " + getMaxSpeed() +
                ", recommended speed: " + getRecommendedSpeed());
    }
}

interface Ford {
    String getColor();
    default int getMaxSpeed() {
        return 100;
    }
}

public class FordClassT implements Car, Ford {
    private static final int MAX_SPEED = 70;
    @Override
    public Integer calculateFuel(int tank) {
        return tank * 9;
    }

    @Override
    public String getColor() {
        return "red";
    }

    @Override
    public int getMaxSpeed() {
        return MAX_SPEED;
    }

    @Override
    public int getRecommendedSpeed() {
        return (int) (MAX_SPEED * Car.RECOMMENDED_SPEED_FACTOR);
    }

    public static void main(String[] args) {
        FordClassT fordClassT = new FordClassT();
        fordClassT.printMaxAndRecommendedSpeed();
    }
}
