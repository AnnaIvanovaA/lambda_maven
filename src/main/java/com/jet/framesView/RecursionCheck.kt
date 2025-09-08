package com.jet.framesView

fun main() {
    rec (0, 0)


    listOf(1,2,3).forEach { println(it) }
    while (true) {
        recursive()
    }

}

fun recursive(x: Int = 42) {
    if (x == 0) {
        return // breakpoint
    }
    recursive(x - 1)
}

fun rec(i: Int, n: Int) {
    if (i > 1000) {
        println(n) // Breakpoint
    }
    return rec(i + 1, n + i)
}