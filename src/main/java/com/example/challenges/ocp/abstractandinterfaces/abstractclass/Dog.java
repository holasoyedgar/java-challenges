package com.example.challenges.ocp.abstractandinterfaces.abstractclass;

abstract class Animal {
    public abstract void speak();
}

abstract class Mammal extends Animal {
    public abstract void walk();
}

public class Dog extends Mammal {
    @Override
    public void walk() {
        System.out.println("Dog's walking");
    }

    @Override
    public void speak() {
        System.out.println("Woof!");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.speak();
        dog.walk();
    }
}
