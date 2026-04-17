package com.example.challenges.ocp.concurrency;

public class RunnableExample {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("Thread '" + Thread.currentThread().getName() + "'  is executing."))
                .start();
        System.out.println("Thread '" + Thread.currentThread().getName() + "'  is executing.");
    }
}
