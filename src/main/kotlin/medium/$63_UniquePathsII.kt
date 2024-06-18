package medium

/**
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner
 * (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can
 * only move either down or right at any point in time.
 *
 * An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any
 * square that is an obstacle.
 *
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The testcases are generated so that the answer will be less than or equal to 2 * 10^ 9.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 100
 * - grid[i][j] is 0 or 1
 *
 * Example 1:
 * Input: grid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 *
 * Example 2:
 * Input: grid = [[0,1],[0,0]]
 * Output: 1
 */
fun main() {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val prevRow = IntArray(obstacleGrid.first().size)
        prevRow[0] = 1

        for (i in obstacleGrid.indices) {
            for (j in obstacleGrid[i].indices) {
                if (obstacleGrid[i][j] == 1) {
                    prevRow[j] = 0
                } else if (j > 0) {
                    prevRow[j] += prevRow[j - 1]
                }
            }
        }

        return prevRow.last()
    }

    val grid1 = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0))
    println(uniquePathsWithObstacles(grid1)) // 2

    val grid2 = arrayOf(intArrayOf(0, 1), intArrayOf(0, 0))
    println(uniquePathsWithObstacles(grid2)) // 1
}
