package medium

import java.util.PriorityQueue

/**
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n. Each CPU interval can be
 * idle or allow the completion of one task. Tasks can be completed in any order, but there's a constraint: there has to
 * be a gap of at least n intervals between two tasks with the same label.
 *
 * Return the minimum number of CPU intervals required to complete all tasks.
 *
 * Constraints:
 * - 1 <= tasks.length <= 10^4
 * - tasks[i] is upper-case English letter.
 * - 0 <= n <= 100
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 *
 * Example 2:
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 * Output: 6
 *
 * Example 3:
 * Input: tasks = ["A","A","A","B","B","B"], n = 3
 * Output: 10
 */
fun main() {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val maxHeap = PriorityQueue<Int> { a, b -> b - a }
        val counts = tasks.groupBy { it }.mapValues { it.value.size }
        counts.values.forEach { maxHeap.offer(it) }
        val queue = ArrayDeque<Pair<Int, Int>>() // Pair<count, coolDown>
        var time = 0

        while (maxHeap.isNotEmpty() || queue.isNotEmpty()) {
            time++

            if (maxHeap.isNotEmpty()) {
                val count = maxHeap.poll()
                if (count > 1) {
                    queue.add(count - 1 to n + time)
                }
            } else {
                time = queue.first().second // fast-forward
            }

            if (queue.isNotEmpty() && queue.firstOrNull()?.second == time) {
                maxHeap.offer(queue.removeFirst().first)
            }
        }

        return time
    }

    println(leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 2)) // 8
    println(leastInterval(charArrayOf('A', 'C', 'A', 'B', 'D', 'B'), 1)) // 6
    println(leastInterval(charArrayOf('A', 'A', 'A', 'B', 'B', 'B'), 3)) // 10
}