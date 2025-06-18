package com.jet.breakpoints;

public class ConditionalBP2 {
    public static void main(String[] args) {
        while(true) {
            new Thread(() -> {
                System.out.println("abc");
            }).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


