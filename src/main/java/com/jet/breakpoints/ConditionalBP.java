package com.jet.breakpoints;

import java.util.Set;

public class ConditionalBP {
    public static void main(String[] args) {
        Set<String> hello = Set.of("Hello", "World", "Boo");
        System.out.println();

        new Thread(() -> {
            while (true) {
                if (hello.contains("Boo")) {
                    System.out.println("Boo");
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        System.out.println();
        System.out.println();
        new Thread(() -> {
            while (true) {
                System.gc();
            }
        }).start();
    }
}
