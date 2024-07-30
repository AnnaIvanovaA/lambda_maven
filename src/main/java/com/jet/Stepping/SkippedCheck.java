package com.jet.Stepping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SkippedCheck {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        int runNumber = 0;
        while (true) {
            for (int i = 0; i < 100; i++) {
                int k = i;
                int n = runNumber;
                executorService.submit(() -> {
                    // Breakpoint with condition `k == 37` here:
                    System.out.println("Run " + n + ", Task #" + k);
                });
            }
            runNumber++;
        }
    }
}
