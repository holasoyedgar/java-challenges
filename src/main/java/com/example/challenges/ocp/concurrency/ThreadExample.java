package com.example.challenges.ocp.concurrency;

public class ThreadExample extends Thread {
    @Override
    public void run() {
        System.out.println("Thread '" + getName() + "'  is executing.");
    }

    public static void main(String[] args) {
        new ThreadExample().start();
        System.out.println("Thread '" + Thread.currentThread().getName() + "' is executing.");
    }
}
