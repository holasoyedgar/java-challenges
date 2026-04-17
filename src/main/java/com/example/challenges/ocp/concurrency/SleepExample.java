package com.example.challenges.ocp.concurrency;

public class SleepExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("Even: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 11; i += 2  ) {
                System.out.println("Odd: " + i);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
