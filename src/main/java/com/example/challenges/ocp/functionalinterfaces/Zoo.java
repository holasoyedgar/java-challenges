package com.example.challenges.ocp.functionalinterfaces;

@FunctionalInterface
interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

public class Zoo {
    public static void main(String[] args) {
        Dog dogClass = new Dog();
        dogClass.speak();

        Animal dogFI = new Animal() {
            @Override
            public void speak() {
                System.out.println("Woof!");
            }
        };

        dogFI.speak();

        Animal dogLambda = () -> System.out.println("Woof!");
        Animal catLambda = () -> System.out.println("Meow!");
        Animal cowLambda = () -> System.out.println("Moo!");
        dogLambda.speak();
        catLambda.speak();
        cowLambda.speak();
    }
}
