package medium

/**
 * Given an array of meeting time interval objects consisting of start and end times
 * [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), determine if a person could add all meetings to their
 * schedule without any conflicts.
 *
 * Constraints:
 * - 0 <= intervals.length <= 500
 * - 0 <= intervals[i].start < intervals[i].end <= 10^6
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 * Input: intervals = [[5,8],[9,15]]
 * Output: true
 *
 * Note:
 * [0,8] and [8,10] are not considered a conflict at 8
 */
fun main() {
    class Interval(var start: Int, var end: Int)

    fun canAttendMeetings(intervals: List<Interval>): Boolean {
        // base case
        if (intervals.size <= 1) return true

        // 1. sort the intervals by start time
        intervals.sortedBy { it.start }
            .let {
                // 2. keep track of the end
                var prevEnd = it[0].end

                // 3. iterate
                for (i in 1..it.lastIndex) {
                    val interval = it[i]
                    // if the start time of the current interval is less than the end time of the previous interval
                    if (interval.start < prevEnd) {
                        return false
                    }
                    // update the end time
                    prevEnd = interval.end
                }
            }

        return true
    }

    // Test cases
    val testCases = listOf(
//        listOf(Interval(0, 30), Interval(5, 10), Interval(15, 20)) to false,
//        listOf(Interval(5, 8), Interval(9, 15)) to true,
        listOf(
            Interval(465, 497), Interval(386, 462), Interval(354, 380),
            Interval(134, 189), Interval(199, 282), Interval(18, 104), Interval(499, 562),
            Interval(4, 14), Interval(111, 129), Interval(292, 345)
        ) to true,
    )

    for ((input, expected) in testCases) {
        val result = canAttendMeetings(input)
        println("Input: $input, Expected: $expected, Result: $result")
    }
}