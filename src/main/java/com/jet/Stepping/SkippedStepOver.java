package com.jet.Stepping;

import java.util.function.Supplier;

public class SkippedStepOver {

    // Thread A starts first and hits breakpoint.
    // We make Step Over, but it hangs waiting for readyToContinueA ...
    // Thread B now can also work and hits conditional breakpoint.
    // Its evaluation is started.
    // Now thread A can continue its stepping, but another thread is evaluating.
    // So we skip steps and notify thread B to stop condition evaluation.
    // Now both threads finish and we've got buggy Step Over.

    static volatile boolean readyToStartB = false;
    static volatile boolean readyToContinueA = false;
    static volatile boolean readyToStopCondition = false;

    public static void main(String[] args) {
        new Thread(() -> {
            //Breakpoint! suspendPolicy(SuspendAll)
            readyToStartB = true; waitUntil(() -> readyToContinueA);
            String s = "I would be skipped :(";
            readyToStopCondition = true;
        }, "A").start();

        new Thread(() -> {
            waitUntil(() -> readyToStartB);
            //Breakpoint! suspendPolicy(SuspendAll) Condition(longFalse())
            String s = "I'm a line with a really long conditional breakpoint";
            // just to prevent dead lock if previous breakpoint's condition was not evaluated:
            longFalse();
        }, "B").start();
    }

    public static boolean longFalse() {
        readyToContinueA = true;
        waitUntil(() -> readyToStopCondition);
        return false;
    }

    private static void waitUntil(Supplier<Boolean> condition) {
        while (!condition.get()) {
            // just nop, or call Thread.onSpinWait() on JDK >= 9
        }
    }

}
