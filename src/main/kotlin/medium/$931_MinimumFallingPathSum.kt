package medium


/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either
 * directly below or diagonally left/right. Specifically, the next element from position (row, col) will be
 * (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * Constraints:
 * - n == matrix.length == matrix[i].length
 * - 1 <= n <= 100
 * - -100 <= matrix[i][j] <= 100
 *
 * Example 1:
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a min sum of 13 (1, 5, 7) and (1, 4, 8).
 *
 * Example 2:
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a min sum of -59 is (-19, -40).
 */
fun main() {
    fun minFallingPathSum(matrix: Array<IntArray>): Int {
        val matrixFirstRow = matrix.first()
        if (matrixFirstRow.size == 1) {
            return matrixFirstRow.first()
        }

        var prevRow = IntArray(matrix.first().size)
        for (i in matrixFirstRow.indices) {
            prevRow[i] = matrixFirstRow[i]
        }

        for (currentRowIdx in 1..matrix.lastIndex) {
            val currentRow = matrix[currentRowIdx]
            prevRow = currentRow.mapIndexed { colIdx, num ->
                num + when (colIdx) {
                    0 -> minOf(prevRow[0], prevRow[1])
                    currentRow.lastIndex -> minOf(prevRow[colIdx], prevRow[colIdx - 1])
                    else -> minOf(prevRow[colIdx - 1], prevRow[colIdx], prevRow[colIdx + 1])
                }
            }.toIntArray()
        }

        return prevRow.min()
    }

    println(minFallingPathSum(arrayOf(intArrayOf(2, 1, 3), intArrayOf(6, 5, 4), intArrayOf(7, 8, 9)))) // 13
    println(minFallingPathSum(arrayOf(intArrayOf(-19, 57), intArrayOf(-40, -5)))) // -64
}