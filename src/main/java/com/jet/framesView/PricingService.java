package com.jet.framesView;


public class PricingService {

    public int calculate(String orderId) {
        int base = basePrice(orderId);
        int discount = discount(orderId);
        return total(base, discount);
    }

    private int basePrice(String orderId) {
        System.out.println(orderId);
        return 100;
    }

    private int discount(String orderId) {
        return computeDiscount(orderId);
    }

    private int computeDiscount(String orderId) {
        return orderId.length() * 2; // Breakpoint
    }

    private int total(int base, int discount) {
        return base - discount;
    }
}

