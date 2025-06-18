package com.jet.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class OperationThreadWithPrio {
    public static void main(String[] args) throws InterruptedException {
        OperationQueue queue = new OperationQueue();

        String str = "fdfgdf";

        MyOperationThread regularThread = new MyOperationThread("operation-thread-regular", queue, false);
        MyOperationThread priorityThread = new MyOperationThread("operation-thread-priority", queue, true);

        regularThread.start();
        priorityThread.start();


        //Breakpoint here and take thread dump
        queue.add((Runnable) () -> System.out.println(Thread.currentThread().getName() + ": Executing regular task"));

        // A task that simulates a CPU-bound calculation
//        queue.add((Runnable)() -> {
//            System.out.println(Thread.currentThread().getName() + ": Simulating CPU-bound task...");
//            long sum = 0;
//            for (int i = 0; i < 1000000; i++) {
//                sum += i;
//            }
//            System.out.println(Thread.currentThread().getName() + ": CPU-bound task result = " + sum);
//        });

        System.out.println("Threads started. Take a thread dump now (e.g., using jstack).");
        Thread.sleep(TimeUnit.MINUTES.toMillis(10));
    }

    static class MyOperationThread extends Thread {
        private final OperationQueue queue;
        private final DummyOperationRunner runner = new DummyOperationRunner();
        private final boolean priority;
        private volatile boolean shutdown;

        public MyOperationThread(String name, OperationQueue queue, boolean priority) {
            super(name);
            this.queue = queue;
            this.priority = priority;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    Object task = queue.take();
                    process(task);

                    // Simulate priority-based behavior
                    if (priority) {
                        // Priority threads process faster
                        Thread.sleep(500);
                    } else {
                        Thread.sleep(1500);
                    }
                }
            } catch (InterruptedException e) {
                // graceful shutdown
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
