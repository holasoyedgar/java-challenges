package com.example.challenges.ocp.collections;

import java.sql.SQLClientInfoException;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return String.format("Person: %s is %d years old", name, age);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
