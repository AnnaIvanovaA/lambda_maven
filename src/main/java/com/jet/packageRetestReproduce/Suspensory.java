package com.jet.packageRetestReproduce;

public class Suspensory {

        public static void main(String[] argv) {
            Thread thread1 = new Thread(new Runnable() {
                @Override public void run() {
                    while (true) {
                        try {
                            System.out.println(System.currentTimeMillis());
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread1.start();
            new Thread(new Runnable() {
                @Override public void run() {
                    while (true) {
                        try {
                            System.out.println("thread 2");
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Holder."); // Breakpoint here.
        }

}
