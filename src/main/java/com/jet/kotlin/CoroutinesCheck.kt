package com.jet.kotlin

import kotlinx.coroutines.*

fun main() = runBlocking {
    val threadCount = 10000

    val threads = mutableListOf<Thread>()

    (0..threadCount).forEach {
        launch(Dispatchers.Default + CoroutineName("MyCoroutine")) {
            println("Coroutine$it ${Thread.currentThread()} is starting heavy computation...")
            delay(100000)
            println("Coroutine$it ${Thread.currentThread()} completed computation.")
        }
    }
    println()
}