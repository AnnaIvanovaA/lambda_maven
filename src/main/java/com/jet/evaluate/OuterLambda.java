package com.jet.evaluate;

public class OuterLambda {
    String msg = "Hello";

    class Inner {
        void run() {
            Runnable r = () -> System.out.println(msg); // set a breakpoint inside lambda
            r.run();
        }
    }

    public static void main(String[] args) {
        new OuterLambda().new Inner().run();
    }
}
