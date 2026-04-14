package com.example.challenges.ocp.records;

import java.time.LocalDate;

record Student(String firstName, String lastName, int id) {
    public Student {
        if (id < 10 || id > 1_000_000) {
            throw new IllegalArgumentException("The id not valid");
        }
        firstName = firstName.substring(0, 1).toUpperCase().concat(firstName.substring(1).toLowerCase());
        lastName = lastName.substring(0, 1).toUpperCase().concat(lastName.substring(1).toLowerCase());

    }
}
public class RecordExamples {
    public static void main(String[] args) {
        Student student1 = new Student("joHn", "cENa", 15);
        System.out.println(student1);
        Student student2 = new Student("John", "Cena", 15);
        System.out.println(student1.equals(student2));
    }
}
