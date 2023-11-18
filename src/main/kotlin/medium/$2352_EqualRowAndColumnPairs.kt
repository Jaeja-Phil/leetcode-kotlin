package medium

/**
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 *
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 *
 * constraints:
 * - n == grid.length == grid[i].length
 * - 1 <= n <= 200
 * - 1 <= grid[i][j] <= 10^5
 *
 * Example 1:
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 *
 * Example 2:
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 */
fun main() {
    val grid = arrayOf(
        intArrayOf(3, 2, 1),
        intArrayOf(1, 7, 6),
        intArrayOf(2, 7, 7)
    )
    println(`Equal Row and Column Pairs`(grid)) // 1

    val grid2 = arrayOf(
        intArrayOf(3, 1, 2, 2),
        intArrayOf(1, 4, 4, 5),
        intArrayOf(2, 4, 2, 2),
        intArrayOf(2, 4, 2, 2)
    )
    println(`Equal Row and Column Pairs`(grid2)) // 3
}

fun `Equal Row and Column Pairs`(grid: Array<IntArray>): Int {
    /**
     * The size of the grid is the same for both rows and columns. (constraints)
     */
    val n = grid.size

    /**
     * create a pairs list to store possible pairs of rows and columns.
     */
    val pairs = mutableListOf<Pair<Int, Int>>()

    /**
     * Find all pairs of rows and columns that have the same first and last elements.
     */
    for (row in 0 ..< n) {
        for (col in 0  ..< n) {
            if (grid[row][0] == grid[0][col] && grid[row][n - 1] == grid[n - 1][col]) {
                pairs.add(row to col)
            }
        }
    }

    /**
     * Count the number of pairs that have the same elements in the same order.
     */
    var total = 0
    pairs.forEach { (row, col) ->
        if ((0 ..< n).count { idx -> grid[row][idx] == grid[idx][col] } == n) {
            total++
        }
    }

    return total
}