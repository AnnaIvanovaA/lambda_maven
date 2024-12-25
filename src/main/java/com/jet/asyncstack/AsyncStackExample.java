package com.jet.asyncstack;

import java.util.concurrent.CompletableFuture;

public class AsyncStackExample {
    public static void main(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Async operation complete");
        });

        future.thenRun(() -> System.out.println("Continuing async execution"))
                .join(); // Blocking the main thread to wait for completion
    }
}
