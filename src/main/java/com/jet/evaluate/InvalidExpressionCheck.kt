package com.jet.evaluate

fun main() {
        val processor = object {
            fun process(numbers: List<Int>) {
                numbers.forEach { num ->
                    val square = num * num
                    println("Processing: $num, Square: $square") // Set a breakpoint here
                }
            }
        }

        val numbers = listOf(1, 2, 3, 4, 5)
        processor.process(numbers)
    }

