package com.jet.breakpoints

fun main() {

    val y = 5
    val x = 6
    val lineBreakpoint = "print me"

    val longString = List(200) { "hello!" }.joinToString(" ")

    val l = listOf(1, 2, 3)
    l.map { 100 * it } // ← breakpoint

}