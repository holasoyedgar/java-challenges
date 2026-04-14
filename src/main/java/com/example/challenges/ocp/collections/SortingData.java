package com.example.challenges.ocp.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingData {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", 35),
                new Person("Edgar", 28),
                new Person("Albert", 45),
                new Person("Juan", 45),
                new Person("Robert", 28),
                new Person("Carlos", 28));

        Comparator<Person> peopleComparator = Comparator.comparingInt(Person::getAge)
                .thenComparing(Person::getName)
                .reversed();
        people.sort(peopleComparator);
        System.out.println(people);
    }
}
