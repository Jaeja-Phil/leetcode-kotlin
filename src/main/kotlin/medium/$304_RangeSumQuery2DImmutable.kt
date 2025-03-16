package medium

/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 * - Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and
 *   lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 * - NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * - int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the
 *   rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * You must design an algorithm where sumRegion works on O(1) time complexity.
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 200
 * - -10^4 <= matrix[i][j] <= 10^4
 * - 0 <= row1 <= row2 < m
 * - 0 <= col1 <= col2 < n
 * - At most 10^4 calls will be made to sumRegion.
 *
 * Example 1:
 * Input
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3],
 * [1, 1, 2, 2], [1, 2, 2, 4]]
 *
 * Output
 * [null, 8, 11, 12]
 *
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 *
 */
fun main() {
    class NumMatrix(matrix: Array<IntArray>) {

        val dp = Array(matrix.size) { IntArray(matrix[0].size) }

        init {
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    dp[i][j] = matrix[i][j] // start with the same value
                    if (i > 0) dp[i][j] += dp[i - 1][j] // add the value from the top
                    if (j > 0) dp[i][j] += dp[i][j - 1] // add the value from the left
                    if (i > 0 && j > 0) dp[i][j] -= dp[i - 1][j - 1] // subtract the value from the top-left (to avoid double counting)
                }
            }
        }

        fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
            var sum = dp[row2][col2]
            if (row1 > 0) sum -= dp[row1 - 1][col2] // subtract the value from the top
            if (col1 > 0) sum -= dp[row2][col1 - 1] // subtract the value from the left
            if (row1 > 0 && col1 > 0) sum += dp[row1 - 1][col1 - 1] // add the value from the top-left (which has been subtracted twice)
            return sum
        }

    }

    val numMatrix = NumMatrix(arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    ))
    println(numMatrix.sumRegion(2, 1, 4, 3)) // 8
    println(numMatrix.sumRegion(1, 1, 2, 2)) // 11
    println(numMatrix.sumRegion(1, 2, 2, 4)) // 12
}