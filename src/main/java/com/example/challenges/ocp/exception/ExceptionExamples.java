package com.example.challenges.ocp.exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionExamples {
    static void readFileThrowingTheException(String fileName) throws IOException {
        FileInputStream stream = new FileInputStream(fileName);
        byte x = (byte) stream.read();
        System.out.println(x);
    }

    static void readFileHandlingTheException(String fileName) {
        try {
            FileInputStream stream = new FileInputStream(fileName);
            byte x = (byte) stream.read();
            System.out.println(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFileMultipleRelatedExceptions(String fileName) {
        try {
            FileInputStream stream = new FileInputStream(fileName);
            byte x = (byte) stream.read();
            System.out.println(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFileMultipleUnrelatedExceptions(String fileName) {
        try {
            FileInputStream stream = new FileInputStream(fileName);
            byte x = (byte) stream.read();
            System.out.println(x);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    static void readFile(String fileName) {
        try (FileInputStream is = new FileInputStream(fileName)) { // try-with-resources.
            int a = is.read();
            // Some code...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readMyFileClass() {
        try (MyFileClass f1 = new MyFileClass(1);
        MyFileClass f2 = new MyFileClass(2)) {
            throw new RuntimeException("Some exception was thrown");
        } catch (Exception e) {
            System.out.println("The exception was caught");
        } finally {
            System.out.println("Finally block");
        }
    }

    static void printFourthElement(int[] a) {
        try {
            System.out.println(a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There is no fourth element in the array. Sorry.");
        }
    }

    public static void main(String[] args) {
        printFourthElement(new int[]{4, 31, 76});

        readMyFileClass();
    }
}
