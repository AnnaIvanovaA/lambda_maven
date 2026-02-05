package com.jet.asyncstack;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncStackTraceOverheadExample {
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private static void invokeLater(Runnable runnable) {
        executor.submit(runnable);
    }

    private static void recursive(int depth, CountDownLatch latch) {
        if (depth == 0) {
            latch.countDown();
            return;
        }

        invokeLater(() -> recursive(depth - 1, latch));
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            CountDownLatch latch = new CountDownLatch(1);
            recursive(1000, latch);
            latch.await();
        }


        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

        long timeNs = System.nanoTime() - start;
        long timeMs = TimeUnit.NANOSECONDS.toMillis(timeNs);

        // run   Execution time: 69 ms
        // debug Execution time: 2080 ms
        System.out.println("Execution time: " + timeMs + " ms");
    }
}