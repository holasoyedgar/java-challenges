package com.example.challenges.ocp.functionalinterfaces;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

@FunctionalInterface
interface Multiplicable {
    int multiply(int a, int b);
}

public class Calculator {
    static void myProbe(int n, Predicate<Integer> predicate) {
        if (predicate.test(n)) {
            System.out.println("The test has passed");
        } else {
            System.out.println("Test failed");
        }
    }
    public static void main(String[] args) {
        Multiplicable multiplicable = (int a, int b) -> a * b;
        System.out.println(multiplicable.multiply(3, 5));

        Predicate<Integer> myPredicate = (a) -> a > 5;
        System.out.println(myPredicate.test(10));

        myProbe(5, n -> n > 2);
        myProbe(5, n -> n == 0);
        myProbe(5, n -> n % 2 == 0);

        myProbe(6, myPredicate);

        BinaryOperator<Integer> bo = Math::max;
        System.out.println(bo.apply(1, 4));
    }
}
