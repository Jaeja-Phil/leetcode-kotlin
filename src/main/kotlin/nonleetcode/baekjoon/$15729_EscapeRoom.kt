package nonleetcode.baekjoon

import java.util.StringTokenizer

fun main() {
    val n = readln().toInt()
    val st: StringTokenizer = StringTokenizer(readln())
    val lights = IntArray(n)
    for (i in lights.indices) {
        lights[i] = st.nextToken().toInt()
    }

    println(`$15729_EscapeRoom`().solve(lights))
}

class `$15729_EscapeRoom` {
    companion object {
        const val OFF = 0
        const val ON = 1
        const val CONSECUTIVE_AFFECTED_LIGHTS = 2
    }

    fun solve(targetLights: IntArray): Int {
        val currentLights = IntArray(targetLights.size)
        var count = 0
        targetLights.forEachIndexed { idx, value ->
            if (value == currentLights[idx]) return@forEachIndexed

            switch(idx, currentLights)
            count++
        }

        return count
    }

    private fun switch(startIdx: Int, lights: IntArray) {
        var switched = 0
        var idx = startIdx
        while (idx <= lights.lastIndex && switched <= CONSECUTIVE_AFFECTED_LIGHTS) {
            lights[idx] = if (lights[idx] == OFF) ON else OFF
            switched++
            idx++
        }
    }
}