package com.jet.Stepping;

public class Divider {
    public static void divide(int a, int b, Callback callback) {

        if (b == 0) {
            callback.failed("Division by zero!");
            return;
        }

        callback.calculated(a / b);
    }
}
