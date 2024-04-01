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
        // base case
        if (intervals.size <= 1) return 0

        // sort intervals by end time
        intervals.sortBy { it[1] }

        var count = 0
        var end = intervals[0][1]
        for (i in 1 ..< intervals.size) {
            if (intervals[i][0] < end) {
                count++
            } else {
                end = intervals[i][1]
            }
        }
        return count
    }
}