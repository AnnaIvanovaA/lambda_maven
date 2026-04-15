package com.jet.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceLoadDemo {

    private static final List<byte[]> memoryHog = new ArrayList<>();
    private static volatile boolean running = true;

    public static void main(String[] args) {
        System.out.println("Starting CPU and memory load demo...");
        System.out.println("Press Ctrl+C to stop.");

        startCpuLoadThreads();
        startMemoryLoadThread();
        startStatusLogger();
    }

    private static void startCpuLoadThreads() {
        int threads = Math.max(2, Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < threads; i++) {
            Thread cpuThread = new Thread(() -> {
                long x = 0;
                while (running) {
                    // Busy work to consume CPU
                    x += Math.sqrt(x + 12345) * Math.random();
                    if (x > 1_000_000_000) {
                        x = 0;
                    }
                }
            }, "cpu-loader-" + i);

            cpuThread.setDaemon(false);
            cpuThread.start();
        }
    }

    private static void startMemoryLoadThread() {
        Thread memoryThread = new Thread(() -> {
            Random random = new Random();

            try {
                while (running) {
                    // Allocate 5 MB chunks
                    byte[] block = new byte[5 * 1024 * 1024];

                    // Touch the array so it is really used
                    for (int i = 0; i < block.length; i += 4096) {
                        block[i] = (byte) random.nextInt(256);
                    }

                    memoryHog.add(block);

                    // Slow down allocations a bit so chart changes are visible
                    Thread.sleep(500);

                    // Keep memory from growing forever
                    if (memoryHog.size() > 20) {
                        memoryHog.subList(0, 5).clear();
                        System.gc(); // only for demo visibility
                    }
                }
            } catch (OutOfMemoryError e) {
                System.err.println("Ran out of memory. Clearing allocated blocks...");
                memoryHog.clear();
                System.gc();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "memory-loader");

        memoryThread.setDaemon(false);
        memoryThread.start();
    }

    private static void startStatusLogger() {
        Thread loggerThread = new Thread(() -> {
            Runtime runtime = Runtime.getRuntime();

            try {
                while (running) {
                    long usedMb = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
                    long totalMb = runtime.totalMemory() / (1024 * 1024);
                    long maxMb = runtime.maxMemory() / (1024 * 1024);

                    System.out.printf(
                            "Used memory: %d MB | Heap: %d MB | Max heap: %d MB | Held blocks: %d%n",
                            usedMb, totalMb, maxMb, memoryHog.size()
                    );

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "status-logger");

        loggerThread.setDaemon(true);
        loggerThread.start();
    }
}