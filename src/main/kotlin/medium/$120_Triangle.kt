package medium

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the
 * current row, you may move to either index i or index i + 1 on the next row.
 *
 * Constraints:
 * - 1 <= triangle.length <= 200
 * - triangle[0].length == 1
 * - triangle[i].length == triangle[i - 1].length + 1
 * - -10^4 <= triangle[i][j] <= 10^4
 *
 * Example 1:
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 *
 * Example 2:
 * Input: triangle = [[-10]]
 * Output: -10
 */
fun main() {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        var prevRow = IntArray(0)
        triangle.forEach { currRow ->
            val replaceRow = IntArray(currRow.size)

            currRow.forEachIndexed { idx, value ->
                when (idx) {
                    0 -> replaceRow[idx] = if (prevRow.isEmpty()) value else prevRow.first() + value
                    prevRow.size -> replaceRow[idx] = prevRow.last() + value
                    else -> {
                        val sumFromLeft = prevRow[idx - 1] + value
                        val sumFromRight = prevRow[idx] + value
                        replaceRow[idx] = minOf(sumFromLeft, sumFromRight)
                    }
                }
            }

            prevRow = replaceRow
        }

        return prevRow.min()
    }

    val triangle1 = listOf(listOf(2), listOf(3, 4), listOf(6, 5, 7), listOf(4, 1, 8, 3))
    println(minimumTotal(triangle1)) // 11

    val triangle2 = listOf(listOf(-10))
    println(minimumTotal(triangle2)) // -10
}
