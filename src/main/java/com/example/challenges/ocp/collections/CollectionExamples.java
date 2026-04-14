package com.example.challenges.ocp.collections;

import java.util.*;

public class CollectionExamples {
    public static void main(String[] args) {
        Collection<String> names = new ArrayList<>();
        System.out.println(names.add("John"));
        System.out.println(names.add("George"));
        System.out.println(names.add("John")); // .add
        System.out.println(names);
        System.out.println(names.remove("John")); // .remove
        System.out.println(names);
        System.out.println(names.remove("Luke"));

        Collection<String> emptyCollection = new ArrayList<>();
        System.out.println(emptyCollection.isEmpty()); // .isEmpty

        System.out.println(names.size()); // .size

        names.clear();
        System.out.println(names.size());

        System.out.println(names.contains("Luke"));
        names.add("John");
        names.add("George");
        names.add("Luke");
        names.removeIf(s -> s.length() > 4);
        System.out.println(names);

        names.forEach(System.out::println);

        String[] namesArray = {"John", "George", "Paul"};
        List<String> namesAsList = Arrays.asList(namesArray);
        List<String> namesOf = List.of(namesArray);
        List<String> namesCopyOf = List.copyOf(namesAsList);

        namesArray[0] = "Ringo";
        System.out.println(Arrays.toString(namesArray));
        System.out.println(namesAsList);
        System.out.println(namesOf);
        System.out.println(namesCopyOf);

        List<Integer> nums = new ArrayList<>(List.of(1, 2, 34, 56, 789));
        nums.replaceAll(n -> n * n);
        System.out.println(nums);

        List<Integer> integerList = List.of(45, -98, 27);
        Integer[] integerArray = integerList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integerArray));

        Set<String> beatlesHS = new HashSet<>();
        System.out.println(beatlesHS.add("John"));
        System.out.println(beatlesHS.add("Ringo"));
        System.out.println(beatlesHS.add("Ringo"));
        System.out.println(beatlesHS.add("George"));

        System.out.println(beatlesHS);

        Set<String> beatlesTS = new TreeSet<>();
        System.out.println(beatlesTS.add("John"));
        System.out.println(beatlesTS.add("Ringo"));
        System.out.println(beatlesTS.add("Ringo"));
        System.out.println(beatlesTS.add("George"));
        System.out.println(beatlesTS.add("Paul"));
        System.out.println(beatlesTS.add("Fifth"));

        System.out.println(beatlesTS);

        Queue<String> colors = new LinkedList<>();
        colors.offer("blue");
        colors.offer("green");
        colors.offer("yellow");
        colors.offer("red");
        System.out.println(colors);
        System.out.println(colors.peek());
        System.out.println(colors.poll());
        System.out.println(colors);
        System.out.println(colors.peek());

        // Deque (double-ended queue)
        Deque<String> stack = new ArrayDeque<>();
        stack.push("red");
        stack.push("yellow");
        stack.push("green");
        stack.push("blue");
        System.out.println("---- Stack ----");
        System.out.println(stack);
        System.out.println(stack.peek());
        stack.push("black");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack);

        Deque<Integer> numbers = new LinkedList<>();
        numbers.offerFirst(1);
        numbers.offerLast(45);
        numbers.offerFirst(27);
        numbers.offerFirst(11);
        numbers.offerLast(25);
        System.out.println(numbers.peekFirst());
        System.out.println(numbers.peekLast());
        System.out.println(numbers);
    }
}
