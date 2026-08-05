package com.jet.navigation.console;

public class HelperStackPrint {
    public static void main(String[] args) {
        for (int i = 0; i < 200_000; i++) {
            log("helper " + i);
        }
    }

    static void log(String text) {
        System.out.println(text);
    }
}
