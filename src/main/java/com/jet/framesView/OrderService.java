package com.jet.framesView;

public class OrderService {

    private final PricingService pricingService = new PricingService();
    private final AuditService auditService = new AuditService();

    public void process(String orderId) {
        validate(orderId);
        int price = pricingService.calculate(orderId);
        auditService.audit(orderId, price);
        save(orderId);
    }

    private void validate(String orderId) {
        log("Validate " + orderId);
        checkState(orderId);
    }

    private void checkState(String orderId) {
        log("Check state " + orderId);
        deepValidation(orderId);
    }

    private void deepValidation(String orderId) {
        slow(); // breakpoint 1
        System.out.println(orderId);
    }

    private void save(String orderId) {
        log("Save " + orderId); // breakpoint 2
    }

    private void slow() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }
    }

    private void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " | OrderService | " + msg);
    }
}

