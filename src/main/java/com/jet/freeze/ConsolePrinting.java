package com.jet.freeze;

public class ConsolePrinting {
    public static void main(String[] args) {
        for (int i = 0; i < 200_000; i++) {
            System.out.println("tick " + i);
        }
    }
}
