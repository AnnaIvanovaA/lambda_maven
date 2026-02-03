package com.jet.framesView;

public class AuditService {

    public void audit(String orderId, int price) {
        logStart(orderId);
        writeRecord(orderId, price);
    }

    private void logStart(String orderId) {
        log("Audit start " + orderId);
    }

    private void writeRecord(String orderId, int price) {
        log("Order=" + orderId + ", price=" + price);  //Breakpoint
    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " | Audit | " + msg);
    }
}

