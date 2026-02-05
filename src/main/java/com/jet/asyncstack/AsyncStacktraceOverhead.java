package com.jet.asyncstack;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncStacktraceOverhead {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        System.out.println("Starting async flood... Watch for IntelliJ notification.");

        //deep async chains
        for (int i = 0; i < 1_000_000; i++) {
            CompletableFuture.runAsync(() -> {
                deepMethod(1000);
            }, executor);

            // Small throttle to prevent OOM, but fast enough to stress the debugger
//            if (i % 1000 == 0) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                }
//            }
        }
    }

    private static void deepMethod(int depth) {
        if (depth > 0) {
            // Nesting async calls creates a heavy 'trace' for the debugger to stitch together
            CompletableFuture.runAsync(() -> deepMethod(depth - 1));
        } else {
            Math.sin(Math.random());
        }
    }

}
