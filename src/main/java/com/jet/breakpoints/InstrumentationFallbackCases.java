package com.jet.breakpoints;

/**
 * Test cases where breakpoint instrumentation must NOT be used (fallback to standard evaluation).
 *
 * TC-11 — condition + log expression simultaneously:
 *          Set BOTH a condition expression ("i == 5") AND a log expression ("i=" + i).
 *          Expected: instrumentation NOT used; standard eval path handles this.
 *
 * TC-12 — class filter set:
 *          Set a conditional breakpoint (condition: "i == 5") with a class filter, e.g. "com.jet.*".
 *          Expected: instrumentation NOT used; filter + condition evaluated normally.
 *
 * TC-13 — "Log message" option enabled (not a custom expression):
 *          In the breakpoint dialog check the "Log message" checkbox (the one that logs the
 *          breakpoint position, not a custom expression).
 *          Expected: instrumentation NOT used.
 *
 * TC-14 — "Log stack trace" option enabled:
 *          In the breakpoint dialog check the "Log stack trace" checkbox.
 *          Expected: instrumentation NOT used.
 */
public class InstrumentationFallbackCases {

    // TC-11: both condition AND log expression set simultaneously
    static void tc11_conditionAndLog() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT: condition "i == 5"  +  log expr "hit: " + i
        }
        System.out.println("TC-11 done");
    }

    // TC-12: class filter set
    static void tc12_classFilter() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT: condition "i == 5",  class filter "com.jet.*"
        }
        System.out.println("TC-12 done");
    }

    // TC-13: "Log message" checkbox enabled (logs breakpoint location, not a custom expression)
    static void tc13_logMessageOption() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT: tick "Log message" option
        }
        System.out.println("TC-13 done");
    }

    // TC-14: "Log stack trace" checkbox enabled
    static void tc14_logStackTrace() {
        for (int i = 0; i < 5; i++) {
            System.out.println("i = " + i);  // ← SET BREAKPOINT: tick "Log stack trace" option
        }
        System.out.println("TC-14 done");
    }

    public static void main(String[] args) {
        tc11_conditionAndLog();
        tc12_classFilter();
        tc13_logMessageOption();
        tc14_logStackTrace();
    }
}
