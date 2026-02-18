package com.jet.kotlin

fun main() {
    triangular(70_00)
}

private fun triangular(num: Int): Int {
    if (num == 0) return 0
    return num + triangular(num - 1)
}