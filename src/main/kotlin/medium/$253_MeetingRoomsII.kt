package medium

import java.util.PriorityQueue

/**
 * Given an array of meeting time interval objects consisting of start and end times
 * [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of days required to schedule all
 * meetings without any conflicts.
 *
 * Constraints:
 * - 0 <= intervals.length <= 500
 * - 0 <= intervals[i].start < intervals[i].end <= 1_000_000
 *
 * Example 1:
 * Input: intervals = [[0,40],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 * Input: intervals = [[4,9]]
 * Output: 1
 */
fun main() {
    data class Interval(val start: Int, val end: Int)

    fun meetingRooms(intervals: Array<IntArray>): Int {
        val sortedIntervals = intervals
            .map { Interval(it[0], it[1]) }
            .sortedBy { it.start }
        val minHeap = PriorityQueue<Interval>(compareBy { it.end })
        var minRooms = 0

        for (interval in sortedIntervals) {
            while (minHeap.isNotEmpty() && interval.start > minHeap.peek().end) {
                minHeap.poll()
            }
            minHeap.offer(interval)
            minRooms = maxOf(minRooms, minHeap.size)
        }

        return minRooms
    }

    println(meetingRooms(arrayOf(intArrayOf(0, 40), intArrayOf(5, 10), intArrayOf(15, 20)))) // Output: 2
    println(meetingRooms(arrayOf(intArrayOf(4, 9)))) // Output: 1
}