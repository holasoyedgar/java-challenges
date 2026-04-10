package com.example.challenges.ocp.flowcontrol;

public class Switch {
    public static void main(String[] args) {
        int a = 0;
        switch (a) {
            case 0:
            case 1:
                System.out.println("Good morning");
                break;
            case 2:
                System.out.println("Hi");
                break;
            default:
                System.out.println("Hello");
        }

        String greeting = switch (a) {
            case 0, 1 -> "Good morning";
            case 2 -> { yield "Hi";}
            default -> "Hello";
        };
        System.out.println(greeting);
    }
}
