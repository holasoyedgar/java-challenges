package com.example.challenges.ocp.io;

import java.io.Console;
import java.util.Arrays;

public class ConsoleExample {
    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) {
            String name = console.readLine("What is your name? ");
            console.format("Hello %s%n", name);
            console.writer().println("-".repeat(20));
            console.printf("123%n");

            char[] password = console.readPassword("Enter your password: ");
            char[] confirmedPassword = console.readPassword("Confirm your password: ");

            if (Arrays.equals(password, confirmedPassword)) {
                console.printf("The passwords match%n");
            } else {
                console.printf("ERROR: The passwords do not match%n");
            }
        } else {
            System.err.println("Console unavailable!");
        }
    }
}
