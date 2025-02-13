package medium

import java.util.PriorityQueue

/**
 * You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
 * where tasks[i] = [enqueueTimei, processingTimei] means that the ith task will be available to process at enqueueTimei
 * and will take processingTimei to finish processing.
 *
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 * - If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * - If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 *   If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * - Once a task is started, the CPU will process the entire task without stopping.
 * - The CPU can finish a task then start a new one instantly.
 *
 * Return the order in which the CPU will process the tasks.
 *
 * Constraints:
 * - tasks.length == n
 * - 1 <= n <= 10^5
 * - 1 <= enqueueTimei, processingTimei <= 10^9
 *
 * Example 1:
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 */
fun main() {
    data class Task(val index: Int, val enqueueTime: Int, val processingTime: Int)

    fun getOrder(tasks: Array<IntArray>): IntArray {
        val sortedTasks = tasks.mapIndexed { index, (enqueueTime, processingTime) ->
            Task(index, enqueueTime, processingTime)
        }.sortedBy { it.enqueueTime }
        val minHeap = PriorityQueue(compareBy<Task> { it.processingTime }.thenBy { it.index })
        val res = mutableListOf<Int>()
        var time = 0
        var idx = 0

        while (idx < tasks.size || minHeap.isNotEmpty()) {
            while (idx < tasks.size && sortedTasks[idx].enqueueTime <= time) {
                minHeap.add(sortedTasks[idx])
                idx++
            }

            if (minHeap.isEmpty()) {
                time = sortedTasks[idx].enqueueTime
                continue
            }

            val task = minHeap.poll()
            time += task.processingTime
            res.add(task.index)
        }

        return res.toIntArray()
    }

    println(
        getOrder(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 4),
                intArrayOf(3, 2),
                intArrayOf(4, 1)
            )
        ).contentToString()
    ) // [0, 2, 3, 1]
}
