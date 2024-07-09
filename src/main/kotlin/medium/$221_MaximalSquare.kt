package medium

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its
 * area.
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 300
 * - matrix[i][j] is '0' or '1'.
 *
 * Example 1:
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 *
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 *
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 */
fun main() {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        val dp = Array(m + 1) { IntArray(n + 1) }
        var max = 0

        for (i in 1..m) {
            for (j in 1..n) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                    max = maxOf(max, dp[i][j])
                }
            }
        }

        return max * max
    }

    val matrix1 = arrayOf(
        charArrayOf('1', '0', '1', '0', '0'),
        charArrayOf('1', '0', '1', '1', '1'),
        charArrayOf('1', '1', '1', '1', '1'),
        charArrayOf('1', '0', '0', '1', '0')
    )
    println(maximalSquare(matrix1)) // 4

    val matrix2 = arrayOf(
        charArrayOf('0', '1'),
        charArrayOf('1', '0')
    )
    println(maximalSquare(matrix2)) // 1

    val matrix3 = arrayOf(
        charArrayOf('0')
    )
    println(maximalSquare(matrix3)) // 0
}