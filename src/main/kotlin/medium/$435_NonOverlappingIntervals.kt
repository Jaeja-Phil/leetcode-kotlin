package medium

/**
 * Given an array of intervals "intervals" where intervals[i] = [starti, endi], return the minimum number of intervals
 * you need to remove to make the rest of the intervals non-overlapping.
 *
 * constraints:
 * - 1 <= intervals.length <= 10^5
 * - intervals[i].length == 2
 * - -5 * 10^4 <= starti < endi <= 5 * 10^4
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 *
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 *
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
fun main() {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortBy { it[0] }

        var count = 0
        var prevEnd = intervals[0][1]
        for (i in 1..intervals.lastIndex) {
            val (start, end) = intervals[i]
            if (start >= prevEnd) {
                prevEnd = end // there is no overlap, update prevEnd
            } else {
                count++ // there is an overlap, increment count
                prevEnd = minOf(prevEnd, end) // update prevEnd to the minimum end time
                /**
                 * why update prevEnd to the minimum end time?
                 * because eliminating interval with bigger end time will allow more intervals to be included
                 */
            }
        }

        return count
    }

    val intervals = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(1, 3)
    )
    println(eraseOverlapIntervals(intervals)) // Output: 1

    val intervals2 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 2),
        intArrayOf(1, 2)
    )
    println(eraseOverlapIntervals(intervals2)) // Output: 2

    val intervals3 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3)
    )
    println(eraseOverlapIntervals(intervals3)) // Output: 0
}