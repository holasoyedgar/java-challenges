package com.example.challenges.ocp.basics;

import java.util.Random;

public class NumberGenerator {
    public static void main(String[] args) {
        Random randomNumber = new Random();
        System.out.println(randomNumber.nextInt(100));
    }
}
