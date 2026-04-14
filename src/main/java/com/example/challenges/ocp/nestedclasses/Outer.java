package com.example.challenges.ocp.nestedclasses;

public class Outer {
    private String str = "Hello!";
    protected class Inner {
        private void print(String s) {
            System.out.println(s);
        }
        public void printTwice() {
            print(str);
            print(str);
        }
    }
    private void callInnerPrintTwice() {
        Inner inner = new Inner();
        inner.printTwice();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.callInnerPrintTwice();

        Inner inner = outer.new Inner();
        inner.printTwice();

        new Outer().new Inner().printTwice();

        State.Town town = new State.Town();
        town.printTown();

        Calculator calculator = new Calculator();
        calculator.calculate();
    }
}

class State {
    public static class Town {
        public void printTown() {
            System.out.println("Town");
        }
    }
}

class Calculator {
    private final int a = 3;
    public void calculate() {
        final int b = 5;
        class Computer {
            public void multiply() {
                System.out.println(a * b);
            }
        }
        Computer computer = new Computer();
        computer.multiply();
    }
}