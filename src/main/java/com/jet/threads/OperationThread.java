package com.jet.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class OperationThread {
    public static void main(String[] args) throws InterruptedException {
        OperationQueue queue = new OperationQueue();
        MyOperationThread thread = new MyOperationThread("operation-thread-1", queue);
        thread.start();

        //Breakpoint here and take thread dump
        queue.add((Runnable)() -> System.out.println(Thread.currentThread().getName() + ": Executing runnable task"));

        System.out.println("Thread started. You can now take a thread dump (e.g., using jstack).");
        Thread.sleep(TimeUnit.MINUTES.toMillis(10));
    }


    static class MyOperationThread extends Thread {
        private final OperationQueue queue;
        private final DummyOperationRunner runner = new DummyOperationRunner();
        private volatile boolean shutdown;

        public MyOperationThread(String name, OperationQueue queue) {
            super(name);
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    Object task = queue.take();
                    process(task);
                }
            } catch (InterruptedException e) {
                // allow graceful exit
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        private void process(Object task) {
            try {
                if (task instanceof Runnable runnable) {
                    runner.run(runnable);
                } else {
                    System.out.println("Unknown task type: " + task);
                }
            } catch (Throwable t) {
                System.err.println("Error processing task: " + t.getMessage());
                t.printStackTrace();
            }
        }

        public void shutdown() {
            shutdown = true;
            interrupt();
        }
    }


    static class OperationQueue {
        private final BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

        public void add(Object task) {
            queue.offer(task);
        }

        public Object take() throws InterruptedException {
            return queue.take();
        }
    }


    static class DummyOperationRunner {
        public void run(Runnable runnable) {
            runnable.run();
        }
    }
}
