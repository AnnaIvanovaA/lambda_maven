package com.jet.threads;

import lombok.SneakyThrows;

import java.time.LocalTime;

public class C {

    @SneakyThrows
    public static void main(String[] args) {

        Thread timePrinter = new Thread(new TimePrinter());
        timePrinter.setDaemon(true);
        timePrinter.start();


        // main thread
        String mainThreadName = Thread.currentThread().getName();
        System.out.println(mainThreadName + ": started method main");
        Thread thread = new Printer();
        //thread.run() -- run thread (for main)
        thread.start();         //create new thread for printer, run thread

        Thread factorialThread = new Thread(new FactorialCalculator(), "factorial-thread"); //2nd arg - thread name
        factorialThread.start();

        Thread ft2 = new Thread(new FactorialCalculator(), "factorial-thread-2");
        Thread ft3 = new Thread(new FactorialCalculator(), "factorial-thread-3");

        ft2.start();
        ft3.start();

        thread.join();  //main will wait until thread (printer) is finished

        Thread current = Thread.currentThread();
        StackTraceElement[] methods = current.getStackTrace();

        for(var info: methods)
            System.out.println(info);

        Thread.sleep(30000);
        System.out.println("at the main method end");
    }

    static class Printer extends Thread {

        public Printer(){
            super("printer-thread");
        }
        @Override
        @SneakyThrows
        public void run() {
            String threadName = getName();
            for (int i = 0; i < 10; i++) {
                System.out.println(threadName + " In custom thread");
                Thread.sleep(4000);
            }
            System.out.println(threadName + " Method run finished invocation");
        }
    }

    static class FactorialCalculator implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            int factorial = 1;
            for (int i = 1 ; i < 9; i++) {
                factorial *= i;
            }
            Thread.sleep(600);
            System.out.println(Thread.currentThread().getName() + ": factorial = " + factorial);
        }
    }

    static class TimePrinter implements Runnable {

        @Override
        @SneakyThrows
        public void run() {
            while (true){
                System.out.println(LocalTime.now());
                Thread.sleep(1000);
            }
        }
    }
}
