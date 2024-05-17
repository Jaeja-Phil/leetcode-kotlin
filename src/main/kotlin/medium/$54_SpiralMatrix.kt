package medium

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 10
 * - -100 <= matrix[i][j] <= 100
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
fun main() {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        var top = 0
        var bottom = matrix.size - 1
        var left = 0
        var right = matrix[0].size - 1

        while (top <= bottom && left <= right) {
            for (i in left..right) {
                result.add(matrix[top][i])
            }
            top++

            for (i in top..bottom) {
                result.add(matrix[i][right])
            }
            right--

            if (top <= bottom) {
                for (i in right downTo left) {
                    result.add(matrix[bottom][i])
                }
                bottom--
            }

            if (left <= right) {
                for (i in bottom downTo top) {
                    result.add(matrix[i][left])
                }
                left++
            }
        }

        return result
    }

    val input = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    println(spiralOrder(input))
}
