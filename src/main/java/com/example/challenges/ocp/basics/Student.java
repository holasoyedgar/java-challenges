package com.example.challenges.ocp.basics;

// Top level class
public class Student {
    private String name = "John";

    public Student() {
        name = "Teddy";
        System.out.println("Constructor of Student");
    }

    {
        System.out.println(name);
        System.out.println("Initializer block");
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.name);
    }
}
