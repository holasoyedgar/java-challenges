package com.example.challenges;

public class Challenge1 {

    public static void main(String[] args) {
        int n = 3;
        int total = 1 << n; // 2^n

        for (int i = 0; i < total; i++) {
            for (int bit = n - 1; bit >= 0; bit--) {
                int value = (i >> bit) & 1;
                System.out.print(value);
            }
            System.out.println();
        }
    }
}