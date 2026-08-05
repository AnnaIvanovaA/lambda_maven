package com.jet.navigation.console;

public class ConsolePrintingTwoMethods {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 400_000; i++) {
            if ((i & 1) == 0) fromA(i);
            else fromB(i);
            Thread.sleep(10);
        }
    }

    static void fromA(int i) { System.out.println("common line " + i); }
    static void fromB(int i) { System.out.println("common line " + i); }
}
