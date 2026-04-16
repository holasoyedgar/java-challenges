package com.example.challenges.ocp.streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExamples {
    public static void main(String[] args) {
        Stream<String> empty = Stream.empty();
        Stream<Integer> integerStream = Stream.of(1);
        Stream<Integer> integersStream = Stream.of(1, 2, 3);
        var list = List.of(1, 4, 5);
        Stream<Integer> fromList = list.stream();
        Stream<Integer> parallelStream = list.parallelStream();

        Stream<Double> generate = Stream.generate(Math::random); // Infinite
        Stream<Integer> iterate = Stream.iterate(1, n -> n + 2); // Infinite
        Stream<Integer> iterateWithPredicate = Stream.iterate(1, n -> n < 50, n -> n + 2); // Finite

        iterateWithPredicate.forEach(System.out::println);

        Stream<String> names = Stream.of("John", "George", "Ringo");
        System.out.println(names.count());

        Stream<String> names2 = Stream.of("John", "George", "Ben");
        names2.min(Comparator.comparingInt(String::length)).ifPresent(System.out::println);

        var namesList = List.of("John", "abc", "21", "Ringo", "34", "John", "DEF");
        System.out.println(namesList.stream().findAny().orElseThrow());
        System.out.println(namesList.stream().findFirst().orElseThrow());

        Predicate<String> stringPredicate = s -> Character.isLetter(s.charAt(0));
        System.out.println(namesList.stream().anyMatch(stringPredicate));
        System.out.println(namesList.stream().allMatch(stringPredicate));
        System.out.println(namesList.stream().noneMatch(stringPredicate));

        Stream<String> characterStream = Stream.of("L", "u", "k", "e");
        System.out.println(characterStream.reduce("", String::concat));


        Stream<String> parallelCharacterStream = Stream.of("L", "u", "k", "e").parallel();
        System.out.println("Parallel: " + parallelCharacterStream.reduce("", String::concat));

        System.out.println(namesList.stream().reduce(0L, (i, s) -> i + s.length(), Long::sum));

        System.out.println(namesList.stream().collect(Collectors.toSet()));

        TreeSet<String> treeSet = new TreeSet<>(namesList);
        System.out.println(treeSet);

        // Intermediate operations
        System.out.println("Intermediate operations");
        namesList.stream().filter(s -> s.length() > 3).forEach(System.out::println); // filter
        iterate.skip(5) // skip
                .limit(10) // limit
                .forEach(i -> System.out.printf("%d, ", i));
        System.out.println();
        namesList.stream().map(String::length).forEach(i -> System.out.printf("%d, ", i)); // map
        System.out.println();

        List<Integer> zero = List.of();
        List<Integer> one = List.of(1);
        List<Integer> many = List.of(2, 3, 5);
        Stream<List<Integer>> combined = Stream.of(zero, one, many);
        System.out.println(combined.flatMap(Collection::stream).toList()); // flatMap

        System.out.println(namesList.stream().sorted().toList()); // sorted
        System.out.println(namesList.stream().sorted(Comparator.comparingInt(String::length)).toList()); // sorted

        Stream<Integer> numbers = Stream.of(1, 24, 24, 1, 65, 23, 4, 2, 23, 1, 1, 1, 1); // distinct
        System.out.println(numbers.distinct().toList());

        // Primitive streams.
        IntStream intStream = IntStream.of(1, 45, 43, 56); // IntStream
        OptionalDouble avg = intStream.average(); // average
        System.out.println(avg.orElseThrow());

        LongStream longStream = LongStream.range(2, 6); // LongStream
        System.out.println(longStream.max().orElseThrow()); // max

        namesList.stream().mapToDouble(String::length).forEach(i -> System.out.printf("%f, ", i)); // mapToDouble
        System.out.println();

        List<List<Integer>> numbersList = List.of(List.of(1), List.of(2, 3, 4), List.of(5, 6, 7, 8, 9));
        System.out.println(numbersList);
        IntStream flattedStream = numbersList.stream().flatMapToInt(list1 -> list1.stream().mapToInt(n -> n)); // flatMapToInt
        flattedStream.forEach(i -> System.out.printf("%d, ", i));
        System.out.println();

        IntSummaryStatistics intSummaryStatistics = IntStream.of(1, 54, -6, 65, 34).summaryStatistics();
        System.out.println(intSummaryStatistics);
        IntSummaryStatistics emptySummaryStatistics = IntStream.of().summaryStatistics();
        System.out.println(emptySummaryStatistics);

        // Spliterator
        List<Integer> integers = List.of(1, 2, 3);
        Stream<Integer> intStream1 = integers.stream();
        Spliterator<Integer> originalSpliterator = intStream1.spliterator();
        Spliterator<Integer> firstSpliterator = originalSpliterator.trySplit();
        firstSpliterator.tryAdvance(System.out::println);
        System.out.println("---");
        firstSpliterator.forEachRemaining(System.out::println);
        System.out.println("---");
        originalSpliterator.tryAdvance(System.out::println);
        System.out.println("---");
        originalSpliterator.tryAdvance(System.out::println);
        originalSpliterator.tryAdvance(System.out::println);
        originalSpliterator.tryAdvance(System.out::println);
        originalSpliterator.tryAdvance(System.out::println);

        System.out.println("---");
        originalSpliterator.forEachRemaining(System.out::println);

        // collect
        Predicate<String> isNumeric = s -> {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
        System.out.println(namesList.stream().filter(s -> Character.isLetter(s.charAt(0))).collect(Collectors.joining("-"))); // joining
        System.out.println(namesList.stream().collect(Collectors.averagingDouble(String::length)));
        System.out.println(namesList.stream().mapToInt(String::length).sum());
        System.out.println(namesList.stream().collect(Collectors.summarizingInt(String::length)));
        System.out.println(namesList.stream().collect(Collectors.partitioningBy(isNumeric)));
        System.out.println(namesList.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println(namesList.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet())));
        System.out.println(namesList.stream().collect(Collectors.toMap(String::length, Function.identity(), (s1, s2) -> s2)));
        System.out.println(namesList.stream().collect(Collectors.teeing(
                Collectors.summingInt(s -> {
                    if (isNumeric.test(s)) {
                        return Integer.parseInt(s);
                    }
                    return 0;
                }),
                Collectors.filtering(isNumeric.negate(), Collectors.toList()),
                Summarize::new
        )));
    }
}

record Summarize(int sum, List<String> names) { }
