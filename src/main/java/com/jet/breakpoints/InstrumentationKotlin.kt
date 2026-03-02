package com.jet.breakpoints

/**
 * Test cases for Kotlin (K2) breakpoint instrumentation.
 *
 * TC-7 — Kotlin conditional breakpoint:
 *         Set breakpoint in tc7KotlinConditional loop, condition: i == 3
 *         Expected: debugger stops when i == 3; instrumentation is used (K2 mode, not fallback eval).
 *
 * TC-8 — Kotlin logging breakpoint:
 *         Set breakpoint in tc8KotlinLogging loop:
 *           • Log expression: "Kotlin value: $i"
 *           • Suspend: None
 *         Expected: log messages appear in debugger console; program is not suspended.
 */

fun main() {
    tc7KotlinConditional()
    tc8KotlinLogging()
}

// TC-7: Kotlin conditional breakpoint (K2 instrumentation)
fun tc7KotlinConditional() {
    for (i in 0 until 10) {
        println("i = $i")  // ← SET BREAKPOINT HERE  |  condition: i == 3
    }
    println("TC-7 done")
}

// TC-8: Kotlin logging breakpoint (suspend = None)
fun tc8KotlinLogging() {
    for (i in 0 until 10) {
        println("i = $i")  // ← SET BREAKPOINT HERE  |  log expr: "Kotlin value: $i"  |  Suspend: None
    }
    println("TC-8 done")
}
