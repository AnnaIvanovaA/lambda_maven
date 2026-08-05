package com.jet.breakpoints

import java.util.Locale

/**
 * Manual cases for IDEA-387231: expression history in the Kotlin logpoint editor.
 *
 * TC-1: Create a logpoint on KOTLIN-HISTORY-1 and submit several expressions.
 *       Use Alt+Up/Alt+Down on Windows/Linux or Option+Up/Option+Down on macOS.
 *       Expected: previous and next expressions are restored in order.
 *
 * TC-2: Close and reopen the same logpoint editor in the same IDE session.
 *       Expected: history is still available.
 *
 * TC-3: Create a logpoint on KOTLIN-HISTORY-2 after filling history on KOTLIN-HISTORY-1.
 *       Expected: the same history is available, not a per-line history.
 *
 * TC-4: Submit the same expression twice in a row.
 *       Expected: the consecutive duplicate is stored only once.
 */
object LogpointExpressionHistoryKotlin {
    @JvmStatic
    fun main(args: Array<String>) {
        runHistoryCases()
    }

    private val sessions = listOf(
        DebugSession("K-200", "Linus", 3, mapOf("language" to "kotlin", "mode" to "logpoint")),
        DebugSession("K-201", "Barbara", 5, mapOf("language" to "kotlin", "mode" to "history")),
        DebugSession("K-202", "Margaret", 8, mapOf("language" to "kotlin", "mode" to "dedupe")),
    )

    private fun runHistoryCases() {
        sessions.forEachIndexed { index, session ->
            val marker = "first-line-${session.id}"

            // KOTLIN-HISTORY-1: set a non-suspending logpoint on the next line.
            // Expression history candidates:
            //   "kotlin id=${session.id}"
            //   "kotlin owner=${session.owner}, score=${session.score}"
            //   describe(session, index, marker)
            //   "duplicate kotlin ${session.id}"
            //   "duplicate kotlin ${session.id}"
            println("KOTLIN-HISTORY-1 ${session.summary()} $marker")
            println("kotlin id=${session.id}")

            println(describe(session, index, marker))
            println("duplicate kotlin ${session.id}")

        }

        for (session in sessions) {
            val normalizedOwner = session.owner.lowercase(Locale.ROOT)
            val tagCount = session.tags.size

            // KOTLIN-HISTORY-2: set another logpoint here after using KOTLIN-HISTORY-1.
            // Expected: Alt/Option+Up shows expressions entered on KOTLIN-HISTORY-1.
            // Extra expressions for this line:
            //   "kotlin normalized=$normalizedOwner"
            //   describe(session, tagCount, "second-line")
            println("KOTLIN-HISTORY-2 $normalizedOwner $tagCount")
        }
    }

    private fun describe(session: DebugSession, index: Int, source: String): String {
        return "$source #$index ${session.id} ${session.owner} ${session.tags["mode"]}"
    }

    private data class DebugSession(
        val id: String,
        val owner: String,
        val score: Int,
        val tags: Map<String, String>,
    ) {
        fun summary(): String = "$id:$owner:$score"
    }
}
