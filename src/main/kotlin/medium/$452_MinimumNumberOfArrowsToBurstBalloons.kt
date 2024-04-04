package medium

import java.util.*

fun main() {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        // base case
        if (points.isEmpty()) return 0

        // sort points by end time
        points.sortBy { it[1] }
        var arrows = 0
        var end = Int.MIN_VALUE
        if (points.isNotEmpty()) {
            end = points[0][1]
            arrows++
        }

        for (i in 1 ..< points.size) {
            if (points[i][0] > end) {
                end = points[i][1]
                arrows++
            }
        }

        return arrows
    }

    println(
        findMinArrowShots(
            arrayOf(
                intArrayOf(10, 16),
                intArrayOf(2, 8),
                intArrayOf(1, 6),
                intArrayOf(7, 12)
            )
        )
    ) // 2
}
