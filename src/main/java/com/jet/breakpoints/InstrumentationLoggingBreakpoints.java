package com.jet.breakpoints;

/**
 * Test cases for Java logging breakpoint instrumentation.
 *
 * TC-6 — Java logging breakpoint (suspend = NONE):
 *         Set breakpoint on the marked line with:
 *           • Log expression: "Counter: " + i
 *           • Suspend: None (uncheck "Suspend" in breakpoint settings)
 *         Expected: no suspension; log messages appear in the debugger console for every hit.
 *
 * Note: "Log expression + Suspend=None" is exactly the scenario where instrumentation IS used.
 *       "Log message" checkbox or "Log stack trace" checkbox are different options — see
 *       InstrumentationFallbackCases for TC-13 / TC-14.
 */
public class InstrumentationLoggingBreakpoints {

    static void doWork(int i) {
        int result = i * i;
        System.out.println("result = " + result);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            doWork(i);  // ← SET BREAKPOINT HERE  |  log expr: "Counter: " + i  |  Suspend: None
        }
        System.out.println("TC-6 done — check debugger console for 10 log lines");
    }
}
