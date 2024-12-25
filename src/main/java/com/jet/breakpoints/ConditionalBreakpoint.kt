package com.jet.breakpoints

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val hello = setOf("Hello", "World")

    runBlocking {
        launch {
            while (true) {
                // set conditional breakpoint here: `hello.containsAll(setOf("Boo", "Moo"))`
                if (hello.contains("Boo")) {
                    println("boo")
                }
                delay(1)
            }
        }
        delay(200)
        launch(Dispatchers.Default) {
            while (true) {
                System.gc()
                delay(10)
            }
        }
    }
}