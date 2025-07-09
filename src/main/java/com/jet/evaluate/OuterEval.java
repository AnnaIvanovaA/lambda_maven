package com.jet.evaluate;

public class OuterEval {
    String f = "f";

    class Inner {
        void foo() {
            System.out.println(f); // stop on a breakpoint, then evaluate f
        }
    }

    class Inner2 extends Inner {}

    void start() {
        new Inner2().foo();
    }

    public static void main(String[] args) {
        new OuterEval().start();
    }
}
