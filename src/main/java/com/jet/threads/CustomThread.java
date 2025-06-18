package com.jet.threads;

public class CustomThread extends Thread {

    boolean priority;

    @Override
    public void run() {
        // Breakpoint here and get a thread dump.
        System.out.println("inside!");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CustomThread();
        t.start();
        t.join();
    }
}