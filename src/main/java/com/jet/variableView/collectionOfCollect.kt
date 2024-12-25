package com.jet.variableView

fun main() {
    val collection = listOf(arrayOf(1, 2, 3))
    println(collection) // Output: [[Ljava.lang.Integer;@HASHCODE]

    collection.forEach { array ->
        array.forEach { element ->
            print("$element ")
        }
    }
    println();
}