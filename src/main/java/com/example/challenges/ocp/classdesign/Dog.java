package com.example.challenges.ocp.classdesign;

public class Dog extends Mammal {
    private final String name;

    public Dog(String name, int age) {
        super(age); // Use of 'super()'.
        this.name = name;
    }

    public Dog() {
        this("Chip", 3); // Usage of 'this()'. It must be at the first line of the constructor.
        System.out.println("Woof!");
    }

    public void bark() {
        System.out.printf("Dog %s (%d) says: woof!%n", name, getAge());
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.bark();
    }
}
