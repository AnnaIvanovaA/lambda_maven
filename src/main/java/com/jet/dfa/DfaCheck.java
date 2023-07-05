package com.jet.dfa;

public class DfaCheck {
    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            if (i % 15 == 0) {
                System.out.println("smth");
            } else if (i % 3 == 0) {
                System.out.println("smth2");
            } else if (i % 5 == 0) {
                System.out.println("smth3");
            } else {
                System.out.println("nothing");
            }

        }
    }
}
