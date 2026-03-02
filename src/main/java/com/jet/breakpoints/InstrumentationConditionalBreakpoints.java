package com.jet.breakpoints;

/**
 * Test cases for Java conditional breakpoint instrumentation.
 * Registry key: debugger.breakpoint.instrumentation = true (default since build 262)
 *
 * TC-1  — condition TRUE:        set breakpoint in tc1 loop, condition: i == 5
 *                                 Expected: stops exactly once (i == 5)
 * TC-2  — condition FALSE:        set breakpoint in tc2 loop, condition: i < 0
 *                                 Expected: never stops, program finishes normally
 * TC-3  — static field in cond:  set breakpoint in tc3 loop, condition: threshold > 0
 *                                 Expected: fires on first hit (threshold == 5)
 * TC-4  — method call in cond:   set breakpoint in tc4 loop, condition: args.length >= 0
 *                                 Expected: fires on first hit (always true)
 * TC-5  — Boolean wrapper:       set breakpoint in tc5 loop, condition: isEven(i)
 *                                 Expected: fires when i is even (0, 2, 4, ...)
 *                                 (regression: instrumentation must unbox Boolean correctly)
 *
 * TC-9  — Tooltip "Instrumented": after any instrumented breakpoint fires,
 *                                  hover the gutter icon — tooltip should say "Instrumented"
 * TC-10 — Internal-mode icon:    run with -Didea.is.internal=true; gutter icon should show overlay
 * TC-20 — Registry OFF:          set debugger.breakpoint.instrumentation=false, then use tc1_conditionTrue —
 *                                 standard evaluation used (JVM suspends every hit)
 * TC-21 — Stepping after hit:    use tc21_steppingAfterHit; after condition fires, Step Over
 *                                 Expected: stepping works, subsequent hits use instrumentation
 * TC-22 — Evaluate at breakpoint: use tc22_evaluateAtBreakpoint; open Evaluate dialog while stopped
 *                                 Expected: evaluation works without conflicts
 */
public class InstrumentationConditionalBreakpoints {

    /** TC-3: public static field — accessible in breakpoint condition expression */
    public static int threshold = 5;

    // ----- TC-1: condition TRUE -----

    static void tc1_conditionTrue() {
        int hit = 0;
        for (int i = 0; i < 10; i++) {
            hit++;  // ← SET BREAKPOINT HERE  |  condition: i == 5
        }
        System.out.println("TC-1 done, hit=" + hit);
    }

    // ----- TC-2: condition FALSE (never fires) -----

    static void tc2_conditionFalse() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT HERE  |  condition: i < 0
        }
        System.out.println("TC-2 done — no suspension expected");
    }

    // ----- TC-3: condition references static field -----

    static void tc3_staticField() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT HERE  |  condition: threshold > 0
        }
        System.out.println("TC-3 done");
    }

    // ----- TC-4: condition uses method call -----

    static void tc4_methodCall(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT HERE  |  condition: args.length >= 0
        }
        System.out.println("TC-4 done");
    }

    // ----- TC-5: Boolean (non-primitive) wrapper -----

    /** Returns Boolean object, NOT boolean primitive — tests unboxing in instrumentation. */
    static Boolean isEven(int i) {
        return i % 2 == 0;
    }

    static void tc5_booleanWrapper() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT HERE  |  condition: isEven(i)
        }
        System.out.println("TC-5 done");
    }

    // ----- TC-21: stepping after instrumented breakpoint fires -----

    static void tc21_steppingAfterHit() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT HERE  |  condition: i == 3
            int doubled = i * 2;             // Step Over from previous line lands here
            System.out.println("doubled = " + doubled);
        }
        System.out.println("TC-21 done");
    }

    // ----- TC-22: evaluate expression while stopped at instrumented breakpoint -----

    static void tc22_evaluateAtBreakpoint() {
        String[] data = {"alpha", "beta", "gamma", "delta", "epsilon"};
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);  // ← SET BREAKPOINT HERE  |  condition: i == 2
            // While paused: open Evaluate dialog, try: data[i].toUpperCase(), i * 10
        }
        System.out.println("TC-22 done");
    }

    // ----- main -----

    public static void main(String[] args) {
        tc1_conditionTrue();
        tc2_conditionFalse();
        tc3_staticField();
        tc4_methodCall(args);
        tc5_booleanWrapper();
        tc21_steppingAfterHit();
        tc22_evaluateAtBreakpoint();
    }
}
