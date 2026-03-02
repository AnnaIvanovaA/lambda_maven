package com.jet.breakpoints;

/**
 * Lifecycle and edge-case tests for breakpoint instrumentation.
 *
 * TC-15 — disable and re-enable during session:
 *          Set conditional breakpoint in tc15 loop (condition: i == 3). Let it fire.
 *          Disable the breakpoint, continue, then re-enable.
 *          Expected: after re-enabling, breakpoint fires correctly; no crash/duplicate instrumentation.
 *
 * TC-16 — change condition of already-instrumented breakpoint:
 *          Set conditional breakpoint in tc16 loop (condition: i == 5). Let it fire.
 *          While paused, open Breakpoints dialog and change condition to i == 10.
 *          Expected: breakpoint now fires at i == 10.
 *
 * TC-17 — breakpoint in not-yet-loaded class:
 *          Set conditional breakpoint in InstrumentationLazilyLoaded.work() before starting debug.
 *          Expected: once the class is loaded, instrumentation is installed and fires.
 *          (See InstrumentationLazilyLoaded.java)
 *
 * TC-18 — reattach debugger:
 *          Set instrumented conditional breakpoint in tc18 loop (condition: i % 10 == 0).
 *          Let it fire. Detach debugger. Reattach to the same process.
 *          Expected: instrumentation state is reset; breakpoint re-instrumented and fires again.
 *
 * TC-19 — exception in condition expression:
 *          Set breakpoint in tc19 loop with condition: arr[100] > 0
 *          (arr has 5 elements → ArrayIndexOutOfBoundsException at every hit).
 *          Expected: IDE shows error/warning gracefully; debug session is not crashed.
 *
 * TC-20 — registry OFF:
 *          Open Registry (Help > Find Action > Registry), set debugger.breakpoint.instrumentation=false.
 *          Use any of the conditional breakpoint loops (e.g. tc20_registryOff, condition: i == 5).
 *          Expected: standard condition evaluation (JVM suspends on every hit, condition in IDE).
 */
public class InstrumentationLifecycle {

    // TC-15: disable / re-enable
    static void tc15_disableReEnable() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT  |  condition: i == 3
        }
        System.out.println("TC-15 done");
    }

    // TC-16: change condition mid-session
    static void tc16_changeCondition() {
        for (int i = 0; i < 15; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT  |  condition: i == 5  (then change to i == 10)
        }
        System.out.println("TC-16 done");
    }

    // TC-17: lazily-loaded class — breakpoint is in InstrumentationLazilyLoaded.work()
    static void tc17_lazyClassLoad() {
        System.out.println("Before lazy load");
        InstrumentationLazilyLoaded.work(5);  // triggers class load; breakpoint should be instrumented then
        System.out.println("After lazy load");
        System.out.println("TC-17 done");
    }

    // TC-18: reattach debugger — long-running loop; detach then reattach while running
    static void tc18_reattach() throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT  |  condition: i % 10 == 0
            Thread.sleep(100);
        }
        System.out.println("TC-18 done");
    }

    // TC-19: condition expression throws an exception
    static void tc19_conditionThrows() {
        int[] arr = {1, 2, 3, 4, 5};
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT  |  condition: arr[100] > 0
        }
        System.out.println("TC-19 done");
    }

    // TC-20: registry OFF — same loop, different debugger config (disable registry key first)
    static void tc20_registryOff() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT  |  condition: i == 5
        }
        System.out.println("TC-20 done");
    }

    public static void main(String[] args) throws InterruptedException {
        tc15_disableReEnable();
        tc16_changeCondition();
        tc17_lazyClassLoad();
        // tc18_reattach();     // uncomment to test reattach; runs for ~20 s
        tc19_conditionThrows();
        tc20_registryOff();
    }
}
