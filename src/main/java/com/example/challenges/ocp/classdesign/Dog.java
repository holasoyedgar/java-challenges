package com.example.challenges.ocp.classdesign;

public class Dog extends Mammal {
    private String name = "Mike";
    {
        System.out.println(name);
    }
    private static int i = 0;
    static {
        System.out.println("static initializer i: " + i);
    }
    {
        i++;
        System.out.println("instance initializer i: " + i);
    }
    Dog() {
        System.out.println("Dog's constructor");
    }
    public static void main(String[] args) {
        System.out.println("Dog's main method");
        new Dog();
        new Dog();
        new Dog();
    }

    static {
        System.out.println("Dog's static method.");
    }
}
