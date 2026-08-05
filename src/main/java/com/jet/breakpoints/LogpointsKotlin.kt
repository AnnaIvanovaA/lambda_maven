package com.jet.breakpoints

fun main(){
    val numbers = listOf(1, 2, 3, 4, 5, 6)
    val users = listOf(
        DemoUser("Ada", true),
        DemoUser("Grace Hopper", true),
        DemoUser("Katherine Johnson", false),
        DemoUser("Margaret Hamilton", true),
    )

    listOf(1, 2, 3)
        .map { i -> i * 2 } // logpoint above this line, for example
        .forEach { println(it) }

    val doubledEvens = numbers
        .asSequence()
        .filter { it % 2 == 0 } // LOGPOINT HERE: "numbers before even filter = ${numbers.joinToString()}"
        .map { it * 2 }
        .toList()

}

private fun loadValue(): Int = 21

private data class DemoUser(
    val name: String,
    val active: Boolean,
)