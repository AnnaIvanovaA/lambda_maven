package com.jet.kotlin

import com.jet.Person
import java.io.File

fun main() {
    val a = 42
    println(a) //bp here

    val file = File("foo")
    println(file) // put breakpoint here

    try {
        null as Person
    } catch (ex: Exception) {
        var t = 12
        val element: StackTraceElement = ex.stackTrace[0]
        var k = 15;
    }
}