package com.example.challenges.ocp.functionalinterfaces;

import java.time.LocalDateTime;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Supplier<LocalDateTime> dateTimeSupplier = LocalDateTime::now;
        System.out.println(dateTimeSupplier.get());

        Consumer<String> greeting = name -> System.out.println("Hello, " + name + "!");
        greeting.accept("Edgar");

        BiConsumer<String, Integer> presentationCard = (name, age) -> System.out.println(name + " is " + age + " years old.");
        presentationCard.accept("John", 45);

        Predicate<Integer> greaterThan10 = n -> n > 10;
        System.out.println(greaterThan10.test(7));
        BiPredicate<Integer, Integer> greaterThan = (n, m) -> n > m;
        System.out.println(greaterThan.test(10, 4));

        Function<Integer, Double> square = n -> (double) (n * n);
        System.out.println(square.apply(5));

        BiFunction<String, Integer, String> stringConcatenator = (s, i) -> s + i;
        System.out.println(stringConcatenator.apply("Hello", 27));

        UnaryOperator<Integer> negative = n -> -n;
        System.out.println(negative.apply(-6));

        UnaryOperator<String> uppercase = String::toUpperCase;
        System.out.println(uppercase.apply("Edgar"));

        BinaryOperator<Integer> adder = Integer::sum;
        System.out.println(adder.apply(2, -67));

        BinaryOperator<Integer> max = Math::max;
        System.out.println(max.apply(3, -10));

        BinaryOperator<String> concat = String::concat;
        System.out.println(concat.apply("John", " Wayne"));

        Consumer<String> consumer1 = System.out::println;
        Consumer<String> consumer2 = (s) -> System.out.println(" Hello " + s);
        Consumer<String> combined = consumer1.andThen(consumer2); // .andThen usage.
        combined.accept("John");
        consumer1.andThen(consumer2).accept("Helllo");

        Function<Integer, Integer> squared = n -> n * n;
        Function<Integer, Integer> triple = n -> 3 * n;
        Function<Integer, Integer> f1 = squared.andThen(triple); // .andThen
        Function<Integer, Integer> f2 = squared.compose(triple); // .compose
        System.out.println(f1.apply(5));
        System.out.println(f2.apply(6));

        Predicate<Integer> gt10 = n -> n > 10;
        Predicate<Integer> lt20 = n -> n < 20;
        Predicate<Integer> p1 = gt10.and(lt20); // .and
        Predicate<Integer> p2 = gt10.or(lt20); // .or
        Predicate<Integer> p3 = lt20.negate(); // .negate

        System.out.println(p1.test(5));
        System.out.println(p2.test(5));
        System.out.println(p3.test(5));
        System.out.println(p1.test(12));
        System.out.println(p1.test(40));
        System.out.println(p3.test(40));

        IntBinaryOperator sum = Integer::sum;
        System.out.println(sum.applyAsInt(2, 3));

        ToDoubleFunction<Integer> toDoubleFunction = n -> n * 0.5;
        System.out.println(toDoubleFunction.applyAsDouble(76));

        DoubleToIntFunction floorFunction = n -> (int) n;
        System.out.println(floorFunction.applyAsInt(12.33212343));

        DoubleBinaryOperator doubleBinaryOperator = (n, m) -> n * m;
        System.out.println(doubleBinaryOperator.applyAsDouble(2.65, 3.456));
    }
}
