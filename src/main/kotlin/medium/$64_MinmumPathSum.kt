package medium

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the
 * sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 200
 * - 0 <= grid[i][j] <= 100
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 -> 3 -> 1 -> 1 -> 1 minimizes the sum.
 *
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 */
fun main() {
    fun minPathSum(grid: Array<IntArray>): Int {
        var prevRow = grid.first().copyOf()
        for (i in 1..prevRow.lastIndex) {
            prevRow[i] = prevRow[i - 1] + prevRow[i]
        }

        for (i in 1..grid.lastIndex) {
            val targetRow = grid[i]
            targetRow.forEachIndexed { idx, value ->
                targetRow[idx] = when (idx) {
                    0 -> prevRow[idx] + value
                    else -> minOf(targetRow[idx - 1] + value, prevRow[idx] + value)
                }
            }
            prevRow = targetRow
        }

        return prevRow.last()
    }

    val grid1 = arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))
    println(minPathSum(grid1)) // 7
    val grid2 = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))
    println(minPathSum(grid2)) // 12

}