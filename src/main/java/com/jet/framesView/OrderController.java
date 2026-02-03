package com.jet.framesView;

public class OrderController {

    private final OrderService orderService = new OrderService();

    public void handle(String orderId) {
        log("Handle async " + orderId);
        orderService.process(orderId);
    }

    public void handleSync(String orderId) {
        log("Handle sync " + orderId);
        orderService.process(orderId);
    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " | Controller | " + msg);
    }
}

