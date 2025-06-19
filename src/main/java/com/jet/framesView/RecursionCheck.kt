package com.jet.framesView

fun main() {
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