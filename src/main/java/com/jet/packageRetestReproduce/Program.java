package com.jet.packageRetestReproduce;

import java.util.concurrent.atomic.AtomicInteger;

public class Program {

    private static Object signal = new Object();
    private static AtomicInteger c = new AtomicInteger();

    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (signal) {
                try {
                    signal.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            c.getAndIncrement();
            System.out.println("Thread starts !"); // <--- set breakpoint here
            System.out.println("Thread: " + Thread.currentThread().getId());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++)
            new Thread(new MyRunnable()).start();

        Thread.sleep(5000);
        // Signal to threads (mb not to all 10, but which are started waiting)
        synchronized (signal) {
            signal.notifyAll();
        }
        // To prevent exit from program
        synchronized (signal) {
            signal.wait();
        }
    }
}
