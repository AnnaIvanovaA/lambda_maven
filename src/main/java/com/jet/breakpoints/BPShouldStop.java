package com.jet.breakpoints;

public class BPShouldStop {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            if (foo(i) == 12 || foo(i) == 10 || foo(i) == 15) {
                continue; //BP
            }
            System.out.println(i);
        }
    }

    private static int foo(int i) {
        return i;
    }
}
