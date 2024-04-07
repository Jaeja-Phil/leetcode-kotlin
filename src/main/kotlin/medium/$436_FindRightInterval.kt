package medium

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 *
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Note that i may equal j.
 *
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i,
 * then put -1 at index i.
 *
 * Constraints:
 * - 1 <= intervals.length <= 2 * 10^4
 * - intervals[i].length == 2
 * - -10^6 <= starti <= endi <= 10^6
 * - The start point of each interval is unique.
 *
 * Example 1:
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 * Example 2:
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end0 = 2.
 *
 * Example 3:
 * Input: intervals = [[1,4],[2,3],[3,4]]
 * Output: [-1,2,-1]
 * Explanation: There is no right interval for [1,4] and [3,4].
 * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 */
fun main() {
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        // base case
        if (intervals.size == 1) {
            return intArrayOf(-1)
        }

        fun binarySearch(intervals: Array<IntArray>, target: Int): Int {
            var left = 0
            var right = intervals.lastIndex
            while (left <= right) {
                val mid = left + (right - left) / 2
                if (intervals[mid].first() == target) {
                    return mid
                } else if (intervals[mid].first() < target) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            return left
        }

        val startMap = mutableMapOf<Int, Int>()
        for (i in intervals.indices) {
            startMap[intervals[i].first()] = i
        }

        val sortedIntervals = intervals.sortedBy { it.first() }

        val result = IntArray(intervals.size)

        for (i in intervals.indices) {
            val index = binarySearch(sortedIntervals.toTypedArray(), intervals[i].last())
            if (index == intervals.size || sortedIntervals[index].first() < intervals[i].last()) {
                result[i] = -1
            } else {
                result[i] = startMap[sortedIntervals[index].first()]!!
            }
        }
        return result
    }

    println(findRightInterval(arrayOf(intArrayOf(1, 2))).contentToString())
    println(findRightInterval(arrayOf(intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(1, 2))).contentToString())
    println(findRightInterval(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 4))).contentToString())
}