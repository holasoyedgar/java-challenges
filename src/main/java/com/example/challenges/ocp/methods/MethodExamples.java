package com.example.challenges.ocp.methods;

public class MethodExamples {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculatePrice(100));
        Calculator calculator2 = new Calculator();
        calculator2.tax = 0.1;
        System.out.println(calculator2.calculatePrice(100));

        Greeter greeter = new Greeter();
        greeter.greet("Hello", "John", "Edgar", "Albert");

        String[] names = {"Charles", "Alejandro", "Israel"};
        greeter.greet("Hi", names);
    }
}

class Calculator {
    public double tax = 0.2;
    public double calculatePrice(double productPrice) {
        final double rate = 0.5;
        return productPrice * (1 + tax) * (1 + rate);
    }
}

class Greeter {
    public void greet(String greeting, String... names) {
        for (String name : names) {
            System.out.printf("%s, %s!%n", greeting, name);
        }
    }
}