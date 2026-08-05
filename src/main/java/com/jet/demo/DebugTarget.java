package com.jet.demo;

public class DebugTarget {

    public static void main(String[] args) throws Exception {
        int tick = 0;

        while (true) {
            System.out.println("Application is working, tick=" + tick);
            tick++;
            Thread.sleep(1_000);
        }
    }
}
