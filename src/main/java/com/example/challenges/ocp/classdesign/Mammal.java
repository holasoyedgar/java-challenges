package com.example.challenges.ocp.classdesign;

public class Mammal {
    static {
        System.out.println("Mammal's static block");
    }

    {
        System.out.println("Mammal's instance initializer");
    }

    Mammal() {
        System.out.println("Mammal's constructor");
    }
}
