package com.jet.breakpoints;

/**
 * Helper for TC-17: simulates a lazily-loaded class.
 *
 * TC-17 — breakpoint in not-yet-loaded class:
 *   1. Set a conditional breakpoint inside work() BEFORE starting the debug session
 *      (condition: result > 10)
 *   2. Start debug. The class is only loaded when InstrumentationLifecycle.tc17_lazyClassLoad()
 *      calls this method.
 *   Expected: once the class is prepared (ClassPrepare event), instrumentation is installed
 *             and the breakpoint fires correctly.
 */
public class InstrumentationLazilyLoaded {

    public static void work(int value) {
        int result = value * value;  // ← SET CONDITIONAL BREAKPOINT HERE  |  condition: result > 10
        System.out.println("Lazy result = " + result);
    }
}
