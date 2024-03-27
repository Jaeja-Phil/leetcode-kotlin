package medium

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either
 * down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach
 * the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 10^9. (within integer range)
 *
 * constraints:
 * - 1 <= m, n <= 100
 *
 * Example 1:
 * Input: m = 3, n = 7
 * Output: 28
 *
 * Example 2:
 * Input: m = 3, n = 2
 * Output: 3
 */
fun main() {
    fun uniquePaths(m: Int, n: Int): Int {
        // base case
        if (m == 1 || n == 1) return 1

        // create 2D dp array of size m x n
        val dp = Array(m) { IntArray(n) }

        // fill the first row and first column with 1
        for (row in dp.indices) {
            dp[row][0] = 1
        }
        for (col in dp[0].indices) {
            dp[0][col] = 1
        }

        // fill the rest with the dp array
        for (row in 1 ..< m) {
            for (col in 1 ..< n) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1]
            }
        }

        return dp[m - 1][n - 1]
    }

    println(uniquePaths(3, 7)) // 28
    println(uniquePaths(3, 2)) // 3
}