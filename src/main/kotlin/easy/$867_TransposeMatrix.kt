package easy

/**
 * Given a 2D integer array matrix, return the transpose of matrix.
 *
 * The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column
 * indices.
 *
 * Constraints:
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 1000
 * - 1 <= m * n <= 10^5
 * - -10^9 <= matrix[i][j] <= 10^9
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 *
 * Example 2:
 * Input: matrix = [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 */
fun main() {
    fun transposeMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val answer = Array(matrix[0].size) { IntArray(matrix.size) }

        for (matrixRowIdx in matrix.indices) {
            for (matrixColIdx in matrix[matrixRowIdx].indices) {
                answer[matrixColIdx][matrixRowIdx] = matrix[matrixRowIdx][matrixColIdx]
            }
        }

        return answer
    }

    println(
        transposeMatrix(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
        ).map { it.toList() }
    )

    println(
        transposeMatrix(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6)
            )
        ).map { it.toList() }
    )
}