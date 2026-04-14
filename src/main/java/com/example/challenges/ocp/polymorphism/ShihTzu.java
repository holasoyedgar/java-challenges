package com.example.challenges.ocp.polymorphism;

class Dog {
    public static boolean canRun() {
        return true;
    }
}

interface CanRun {
    boolean canRunFaster();
}

public class ShihTzu extends Dog implements CanRun {
    private final int weight = 6;

    @Override
    public boolean canRunFaster() {
        return false;
    }

    public static boolean canRun() {
        return false;
    }

    public static void main(String[] args) {
        ShihTzu shihTzu = new ShihTzu();
        System.out.println(shihTzu.weight);
        System.out.println(canRun());
        System.out.println(shihTzu.canRunFaster());
        System.out.println(Dog.canRun());
        CanRun canRun = shihTzu;
        System.out.println(canRun.canRunFaster());
    }
}
