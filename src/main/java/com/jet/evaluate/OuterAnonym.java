package com.jet.evaluate;

public class OuterAnonym {
    String name = "Outer";

    void test() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(name); // Breakpoint here
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        new OuterAnonym().test();
    }
}
