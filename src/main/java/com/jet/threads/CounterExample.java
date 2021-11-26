package com.jet.threads;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


public class CounterExample {

    @SneakyThrows
    public static void main(String[] args) {

        //SynchronizedCounter counter = new SynchronizedCounter();
        //Counter counter = new ReentrantLockCounter();
        Counter counter = new NonBlockingCounter();

        Thread t1 = new Thread(new Incrementor(counter));
        Thread t2 = new Thread(new Incrementor(counter));
        Thread t3 = new Thread(new Incrementor(counter));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Counter value: " + counter.getValue());
    }

    static class SynchronizedCounter  implements Counter{

        private final Object mutex = new Object();

        private int value;

        public void increment(){
            synchronized (mutex){
                value++;
            }

        }

        public void synchronizedBlockThisIncrement(){
            //not synchronized
            //...
            synchronized (this){
                value++;
            }
            //not synchronized
            //...
        }

        public synchronized void synchronizedIncrement(){
            value++;
        }

        public int getValue(){
            return value;
        }

    }

    @RequiredArgsConstructor
    static class Incrementor implements Runnable{
        private final Counter counter;

        @Override
        @SneakyThrows
        public void run() {
            for (int i = 0; i < 40; i++) {
                counter.increment();
                Thread.sleep(100);
            }
        }
    }
}
