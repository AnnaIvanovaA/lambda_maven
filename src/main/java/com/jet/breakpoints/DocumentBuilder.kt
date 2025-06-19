package com.jet.breakpoints

class DocumentBuilder {
    private val listBasedValue: MutableList<String> = mutableListOf()
    private val stringBuilderBasedValue: StringBuilder = StringBuilder()
    private var varBasedValue: String = ""

    private fun initializeData() {
        listBasedValue.add("List will fail!")
        stringBuilderBasedValue.append("StringBuilder will fail!")
        varBasedValue = "Assignment works!"
    }

    override fun toString(): String = buildString {
        // (A) Put a breakpoint at one of the two upcoming code-lines -> Debugger Warning Bubble -> List + StringBuilder values will appear double
        initializeData()
        appendLine(listBasedValue.joinToString(""))

        // (B) Put a breakpoint at the next code-line -> Debugger Warning Bubble -> Only StringBuilder value will appear double
        appendLine(stringBuilderBasedValue.toString())

        // (C) Put a breakpoint at the next code-line -> All values will appear only once (like expected!)
        appendLine(varBasedValue)
    }

}

fun main() {
    // (D) Put a breakpoint at the next code-line -> All values will appear only once (like expected!)
    val builder = DocumentBuilder()

    // (E) Put a breakpoint at the next code-lines -> List + StringBuilder values will appear double (NO WARNING BUBBLE!)
    val result = builder.toString()

    // (F) Put a breakpoint at the next code-line -> All values will appear only once (like expected!)
    println(result)
}