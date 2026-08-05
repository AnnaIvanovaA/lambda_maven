package com.jet.breakpoints

/** Kotlin-only breakpoint instrumentation fallback scenarios. */
inline fun statisticsInline(block: () -> Unit) {
    block() // BREAKPOINT INSIDE KOTLIN INLINE FUNCTION: condition "System.nanoTime() > 0".
}

fun main() {
    val value = 42
    statisticsInline {
        println("inline value=$value")
    }
}
