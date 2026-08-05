package com.jet.freeze;

import java.util.concurrent.TimeUnit;

public class ConsolePrinting {
    public static void main(String[] args) throws InterruptedException {
        var startNanos = System.nanoTime();
        loop();

        var endNanos = System.nanoTime();
        var elapsedNanos = endNanos - startNanos;
        //System.out.println("Elapsed time: " + TimeUnit.NANOSECONDS.toMillis(elapsedNanos) + " ms");


        Thread.sleep(10000);
        for (int i = 0; i < 200_000; i++) {
            System.out.println("tick " + i);
        }

        for (int i = 0; i < 100_000; i++) {
            if ((i & 1) == 0) fromA();
            else fromB();
        }
    }

    static void fromA() { System.out.println("common line"); }
    static void fromB() { System.out.println("common line"); }

    private static void loop() {
        for (int i = 0; i < 150_000; i++) {
            System.out.println("Perform " + i);
            int z = i * i;
        }
    }
}
