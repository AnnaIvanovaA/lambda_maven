package com.jet.evaluate;

public class Outer1 {
    class Inner {
        void run() {
            System.out.println("No outer usage"); // set a breakpoint here

        }
    }

    public static void main(String[] args) {
        new Outer1().new Inner().run();
    }
}
