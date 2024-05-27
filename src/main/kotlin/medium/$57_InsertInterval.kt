package medium

/**
 * You are given an array of non-overlapping intervals "intervals" where intervals[i] = [starti, endi] represent the start
 * and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval
 * newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * Constraints:
 * - 0 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^5
 * - intervals is sorted by starti in ascending order
 * - newInterval.length == 2
 * - 0 <= start <= end <= 10^5
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 */
fun main() {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        // iterate intervals and add non-overlapping intervals before newInterval
        var i = 0
        while (i < intervals.size && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i])
            i++
        }

        // merge overlapping intervals
        while (i < intervals.size && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = minOf(newInterval[0], intervals[i][0])
            newInterval[1] = maxOf(newInterval[1], intervals[i][1])
            i++
        }
        result.add(newInterval)

        // add non-overlapping intervals after newInterval
        while (i < intervals.size) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    val testCases = arrayOf(
        arrayOf(intArrayOf(1, 3), intArrayOf(6, 9)) to intArrayOf(2, 5), // [[1,5],[6,9]]
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(3, 5),
            intArrayOf(6, 7),
            intArrayOf(8, 10),
            intArrayOf(12, 16)
        ) to intArrayOf(4, 8) // [[1,2],[3,10],[12,16]]
    )

    for (case in testCases) {
        println(insert(case.first, case.second).contentDeepToString())
    }
}
