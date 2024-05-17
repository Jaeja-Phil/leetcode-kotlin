package medium

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * constraints:
 * - matrix.length == n
 * - matrix[i].length == n
 * - 1 <= n <= 20
 * - -1000 <= matrix[i][j] <= 1000
 *
 * Example 1:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 *
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
fun main() {
    fun rotate(matrix: Array<IntArray>) {
        var left = 0
        var right = matrix.lastIndex
        while (left < right) {
            for (cell in 0 ..< (right - left)) {
                val top = left
                val bot = right
                val topLeft = matrix[top][left + cell]
                // swapping
                matrix[top][left + cell] = matrix[bot - cell][left]
                matrix[bot - cell][left] = matrix[bot][right - cell]
                matrix[bot][right - cell] = matrix[top + cell][right]
                matrix[top + cell][right] = topLeft
            }
            left++
            right--
        }
    }

    val input = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
    rotate(input)
    println(input.forEach { println(it.joinToString()) })

    val input2 = arrayOf(
        intArrayOf(5, 1, 9, 11),
        intArrayOf(2, 4, 8, 10),
        intArrayOf(13, 3, 6, 7),
        intArrayOf(15, 14, 12, 16)
    )
    rotate(input2)
    println(input2.forEach { println(it.joinToString()) })
}