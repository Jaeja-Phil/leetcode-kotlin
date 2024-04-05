package easy

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number
 * of negative numbers in grid.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 100
 * - -100 <= grid[i][j] <= 100
 *
 * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 *
 * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 */
fun main() {
    fun countNegatives(grid: Array<IntArray>): Int {
        // base case
        // if first row, first column element is negative, all are negatives
        if (grid.first().first() < 0) {
            return grid.size * grid.first().size
        }

        // if last row, last column element is not negative, there are no negatives
        if (grid.last().last() >= 0) {
            return 0
        }

        var count = 0
        /**
         * start firstNegativeColumnIndex with the last column, as we iterate, as all the grids are sorted, we can update
         * the firstNegativeColumnIndex index
         */
        var firstNegativeColumnIndex = grid.first().size
        for (row in grid) {
            /**
             * when firstNegativeColumn reaches 0, it means all the elements in the row are negative
             * - add the count of the row size to the count
             * when firstNegativeColumn is not 0, it means there are some possibility of positive elements in the row
             * - from firstNegativeColumn, iterate to the left and find the first negative element and update the
             *   firstNegativeColumnIndex
             */
            while (firstNegativeColumnIndex > 0 && row[firstNegativeColumnIndex - 1] < 0) {
                firstNegativeColumnIndex--
            }
            count += row.size - firstNegativeColumnIndex
        }

        return count
    }

    println(
        countNegatives(
            arrayOf(
                intArrayOf(4, 3, 2, -1),
                intArrayOf(3, 2, 1, -1),
                intArrayOf(1, 1, -1, -2),
                intArrayOf(-1, -1, -2, -3)
            )
        )
    ) // 8

    println(
        countNegatives(
            arrayOf(
                intArrayOf(3, 2),
                intArrayOf(1, 0)
            )
        )
    ) // 0
}