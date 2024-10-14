package com.jet.evaluate;

import java.util.Optional;

public class ProtectedEvaluate {
    protected int myProtected = 2;

    public static void main(String[] args) {
        lockingRun(() ->
                // Put breakpoint here on Optional.of and evaluate at least until .map(value -> value)
                Optional.of(new ProtectedEvaluate().myProtected)
                        .map(value -> value)
                        .get()
        );
    }

    public static void lockingRun(Runnable runnable) {
        runnable.run();
    }
}
