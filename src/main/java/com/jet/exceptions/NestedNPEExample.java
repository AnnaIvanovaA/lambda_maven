package com.jet.exceptions;

public class NestedNPEExample {
    public static void main(String[] args) {
        try {
            methodThatThrowsNPE();
        } catch (NullPointerException e) {
            throw new RuntimeException("An error occurred", e);
        }
    }

    public static void methodThatThrowsNPE() {
        String str = null;
        str.length(); // This will throw NullPointerException
    }
}