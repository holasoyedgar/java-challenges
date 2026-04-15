package com.example.challenges.ocp.streams;

import java.util.Optional;
import java.util.function.Function;

public class OptionalExamples {
    public static void main(String[] args) {
        Optional<Double> average = calculateAverage(1, 2, 4, 5);
        Optional<Double> emptyAverage = calculateAverage();
        //System.out.println(emptyAverage.get()); // Will throw NoSuchElementException.
        average.ifPresent(System.out::println);
        if (emptyAverage.isPresent()) {
            System.out.println(emptyAverage.get());
        }

        System.out.println(emptyAverage.orElseGet(Math::random));
        System.out.println(emptyAverage.orElse(Double.NaN));
        System.out.println(average.orElse(Double.NaN));
        // System.out.println(emptyAverage.orElseThrow()); // Will throw NoSuchElementException.
        // System.out.println(emptyAverage.orElseThrow(IllegalStateException::new)); // Will throw IllegalStateException.
    }

    private static Optional<Double> calculateAverage(int... numbers) {
        if (numbers.length == 0) {
            return Optional.empty();
        }
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return Optional.of((double) sum / numbers.length);
    }
}
