package medium

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array
 * of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Constraints:
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^4
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
fun main() {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        // 1. sort the intervals by the start value
        intervals.sortBy { it[0] }

        // 2. create a variable to store the current interval
        var start = intervals[0][0]
        var end = intervals[0][1]

        // 3. iterate through the intervals
        for (i in 1 ..< intervals.size) {
            val interval = intervals[i]
            // 4. if the current interval's start is less than or equal to the end of the previous interval, merge the
            // intervals
            if (interval[0] <= end) {
                end = maxOf(end, interval[1])
            } else {
                // 5. if the current interval's start is greater than the end of the previous interval, add the previous
                // interval to the result
                result.add(intArrayOf(start, end))
                start = interval[0]
                end = interval[1]
            }
        }

        // 4. check & add the last interval
        if (result.isEmpty() || result.last()[1] < start) {
            result.add(intArrayOf(start, end))
        }

        return result.toTypedArray()
    }

    val example1 = arrayOf(intArrayOf(1, 3), intArrayOf(2, 6), intArrayOf(8, 10), intArrayOf(15, 18))
    println(merge(example1).joinToString { it.contentToString() }) // Output: [[1,6],[8,10],[15,18]]
    println("----------")
    val example2 = arrayOf(intArrayOf(1, 4), intArrayOf(4, 5))
    println(merge(example2).joinToString { it.contentToString() }) // Output: [[1,5]]
}