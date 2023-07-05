package com.jet.reproduce;

public class Repro1 {
    public static void main(String[] args) {
        while (true) {
            try {
                throw new UnsupportedOperationException("message", new AssertionError("failed"));
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
        }
    }
}
