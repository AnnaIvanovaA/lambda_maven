package com.jet.exceptions;

public class CauseNestedExample {
    public static void main(String[] args) {
        try {
            throwExceptionWithCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void throwExceptionWithCause() {
        try {
            String str = null;
            str.length(); // NullPointerException occurs here
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid argument due to NPE", e);
        }
    }
}
