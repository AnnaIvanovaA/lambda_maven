package com.jet.framesView;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class MainApp {

    public static void main(String[] args) throws InterruptedException {
        OrderController controller = new OrderController();
        Thread.sleep(100000);

        try (ExecutorService executor = newFixedThreadPool(3)) {

            executor.submit(() -> controller.handle("ORDER-1"));
            executor.submit(() -> controller.handle("ORDER-2"));
            executor.submit(() -> controller.handle("ORDER-3"));

            controller.handleSync("ORDER-SYNC");

            executor.shutdown();
        }
    }
}

