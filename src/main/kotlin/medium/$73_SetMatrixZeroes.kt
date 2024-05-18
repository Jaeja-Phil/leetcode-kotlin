package medium

/**
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[0].length
 * - 1 <= m, n <= 200
 * - -2^31 <= matrix[i][j] <= 2^31 - 1
 *
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 */
fun main() {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size
        var isColZero = false

        for (i in 0 ..< m) {
            // check if the first column has 0
            if (matrix[i][0] == 0) isColZero = true
            for (j in 1 ..< n) {
                // if any element is 0, set the first element of that row and column to 0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0
                    matrix[0][j] = 0
                }
            }
        }

        // set the entire row and column to 0 if the first element of that row or column is 0
        for (i in 1 ..< m) {
            for (j in 1 ..< n) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0
                }
            }
        }

        // set the first row to 0 if the first element of that row is 0
        if (matrix[0][0] == 0) {
            for (j in 0 ..< n) {
                matrix[0][j] = 0
            }
        }

        // set the first column to 0 if isColZero is true
        if (isColZero) {
            for (i in 0 ..< m) {
                matrix[i][0] = 0
            }
        }
    }

    val matrix1 = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 1)
    )
    setZeroes(matrix1)
    matrix1.forEach { println(it.joinToString()) }

    val matrix2 = arrayOf(
        intArrayOf(0, 1, 2, 0),
        intArrayOf(3, 4, 5, 2),
        intArrayOf(1, 3, 1, 5)
    )
    setZeroes(matrix2)
    matrix2.forEach { println(it.joinToString()) }
}
