package nonleetcode.baekjoon

import kotlin.collections.ArrayDeque

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    println(`숨바꼭질`().solve(n, k))
}

class `숨바꼭질` {
    fun solve(n: Int, k: Int): Int {
        if (n == k) return 0

        val queue = ArrayDeque<Int>()
        val visited = BooleanArray(100001) { false }
        queue.add(n)
        visited[n] = true
        var time = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 ..< size) {
                val current = queue.removeFirst()
                if (current * 2 == k || current + 1 == k || current - 1 == k) return time + 1

                if (current - 1 in 0..100000 && !visited[current - 1]) {
                    queue.add(current - 1)
                    visited[current - 1] = true
                }
                if (current + 1 in 0..100000 && !visited[current + 1]) {
                    queue.add(current + 1)
                    visited[current + 1] = true
                }
                if (current < k && current * 2 in 0..100000 && !visited[current * 2]) {
                    queue.add(current * 2)
                    visited[current * 2] = true
                }
            }
            time++
        }

        return -1
    }
}