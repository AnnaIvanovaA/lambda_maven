package com.jet.dfa;

public class DFAExample {
    public static void main(String[] args) {
        foo(0);
        foo(1);
    }

    static void foo(int i) {
        if (i == 0) {
            System.out.println("zero"); // ← breakpoint
        }
    }
}
