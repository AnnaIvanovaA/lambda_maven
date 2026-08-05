package com.jet.breakpoints

import java.lang.StringBuilder

object LogpointsInKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
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

        val joinedNames = users
            .map { it.name } // LOGPOINT HERE: "users before name map = ${users.map { it.name }}"
            .filter { it.length > 3 }
            .joinToString()

        val rawNumbers = listOf("1", "2", "bad", "4")
        val parsedTotal = rawNumbers
// LOGPOINT HERE: should be before mapNotNull { ... }, not inside the lambda.
// Log expression: "raw number tokens = $rawNumbers"
            .mapNotNull { it.toIntOrNull() }
            .sum()

        val firstUser = users.firstOrNull()
        val activeUserLabel = firstUser
// LOGPOINT HERE: should be before let { ... }, not inside the lambda.
// Log expression: "first user before let = $firstUser"
            ?.let { it.name.trim() }
            ?.takeIf { it.isNotEmpty() }
            ?: "anonymous"

        val builder = StringBuilder().append("prefix")
        val builderText = builder
// LOGPOINT HERE: should be before apply { ... }, not inside the lambda.
// Log expression: "builder before apply = ${builder}"
            .apply { append("-body") }
            .also { println("builder length = ${it.length}") }
            .toString()

        val loadedValue = runCatching { loadValue() }
        val recoveredValue = loadedValue
// LOGPOINT HERE: should be before map { ... }, not inside the lambda.
// Log expression: "loaded result before map = ${loadedValue.getOrNull()}"
            .map { it * 2 }
            .onSuccess { println("value = $it") }
            .getOrDefault(0)

        val selectedUsers = users
            .asSequence()
// LOGPOINT HERE: should be before filter { ... }, not inside the lambda.
// Log expression: "active flags = ${users.map { it.name to it.active }}"
            .filter { it.active }
            .map { it.name.uppercase() }
            .toList()

        println("doubledEvens = $doubledEvens")
        println("joinedNames = $joinedNames")
        println("parsedTotal = $parsedTotal")
        println("activeUserLabel = $activeUserLabel")
        println("builderText = $builderText")
        println("recoveredValue = $recoveredValue")
        println("selectedUsers = $selectedUsers")
    }

    private fun loadValue(): Int = 21

    private data class DemoUser(
        val name: String,
        val active: Boolean,
    )
}
