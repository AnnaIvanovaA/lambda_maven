package com.jet.framesView;

public class FramesCheck {
    public static void main(String[] args) throws InterruptedException {
        FramesCheck example = new FramesCheck();

        Thread worker1 = new Thread(() -> {
            example.processOrder("ORDER-1");
        }, "Worker-1");

        Thread worker2 = new Thread(() -> {
            example.processOrder("ORDER-2");
        }, "Worker-2");

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();
    }

    private void processOrder(String orderId) {
        validateOrder(orderId);
        calculatePrice(orderId);
        saveOrder(orderId);
    }

    private void validateOrder(String orderId) {
        log("Validating " + orderId);
        checkPermissions(orderId);
    }

    private void checkPermissions(String orderId) {
        log("Checking permissions for " + orderId);
        slowOperation(); // BP1
    }

    private void calculatePrice(String orderId) {
        log("Calculating price for " + orderId);
        int price = basePrice(orderId) + tax(orderId);
        log("Price = " + price);
    }

    private int basePrice(String orderId) {
        return 100;
    }

    private int tax(String orderId) {
        return 20;
    }

    private void saveOrder(String orderId) {
        log("Saving " + orderId);  //BP2
    }

    private void slowOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }

    private void log(String message) {
        System.out.println(Thread.currentThread().getName() + ": " + message);
    }
}
