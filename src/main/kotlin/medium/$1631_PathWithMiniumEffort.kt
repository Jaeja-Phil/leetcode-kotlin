package medium

import java.util.*
import kotlin.math.abs

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where
 * heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you
 * hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or
 * right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 * Constraints:
 * - rows == heights.length
 * - columns == heights[i].length
 * - 1 <= rows, columns <= 100
 * - 1 <= heights[i][j] <= 10^6
 *
 * Example 1:
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 * Example 2:
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better
 * than route [1,3,5,3,5].
 *
 * Example 3:
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 */
fun main() {
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        val rows = heights.size
        val cols = heights[0].size
        val directions =
            arrayOf(
                0 to 1,
                1 to 0,
                0 to -1,
                -1 to 0
            )
        val visited = Array(rows) { BooleanArray(cols) }
        val pq = PriorityQueue<Pair<Int, Pair<Int, Int>>>(compareBy { it.first })
        pq.add(0 to (0 to 0))

        while (pq.isNotEmpty()) {
            val (effort, pair) = pq.poll()
            val (row, col) = pair
            if (row == rows - 1 && col == cols - 1) return effort

            visited[row][col] = true

            for ((dx, dy) in directions) {
                val newRow = row + dx
                val newCol = col + dy
                if (newRow in 0..<rows && newCol in 0..<cols && !visited[newRow][newCol]) {
                    val newEffort = maxOf(effort, abs(heights[newRow][newCol] - heights[row][col]))
                    pq.add(newEffort to (newRow to newCol))
                }
            }
        }

        return -1
    }

    println(
        minimumEffortPath(
            arrayOf(
                intArrayOf(1, 2, 2),
                intArrayOf(3, 8, 2),
                intArrayOf(5, 3, 5)
            )
        )
    ) // 2

    println(
        minimumEffortPath(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(3, 8, 4),
                intArrayOf(5, 3, 5)
            )
        )
    ) // 1

    println(
        minimumEffortPath(
            arrayOf(
                intArrayOf(1, 2, 1, 1, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 1, 1, 2, 1),
            )
        )
    ) // 0
}
