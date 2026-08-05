package com.jet.breakpoints;

public class SomeExceptionBreakpoint  extends RuntimeException{

    public static void main(String[] args) {
        try {
            processUser("42a");
        } catch (NumberFormatException e) {
            System.out.println("Handled: " + e.getMessage());
        }
    }

    private static void processUser(String value) {
        int number = Integer.parseInt(value); // <- Put caret on NumberFormatException class somewhere
        System.out.println(number);
    }
}
