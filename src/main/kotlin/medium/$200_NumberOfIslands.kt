package medium

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of
 * islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 300
 * - grid[i][j] is '0' or '1'.
 *
 * Example 1:
 * Input: grid = [
 *  ["1","1","1","1","0"],
 *  ["1","1","0","1","0"],
 *  ["1","1","0","0","0"],
 *  ["0","0","0","0","0"]
 *  ]
 *  Output: 1
 *
 *  Example 2:
 *  Input: grid = [
 *  ["1","1","0","0","0"],
 *  ["1","1","0","0","0"],
 *  ["0","0","1","0","0"],
 *  ["0","0","0","1","1"]
 *  ]
 *  Output: 3
 *
 */
fun main() {
    fun numIslands(grid: Array<CharArray>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        fun dfs(i: Int, j: Int) {
            if (
                i to j in visited ||
                i < 0 || i >= grid.size ||
                j < 0 || j >= grid[i].size ||
                grid[i][j] == '0'
            ) {
                return
            }

            visited.add(i to j)
            dfs(i - 1, j)
            dfs(i + 1, j)
            dfs(i, j - 1)
            dfs(i, j + 1)
        }

        var count = 0
        repeat(grid.size) { rowIdx ->
            repeat(grid[rowIdx].size) { colIdx ->
                if (grid[rowIdx][colIdx] == '1' && rowIdx to colIdx !in visited) {
                    dfs(rowIdx, colIdx)
                    count++
                }
            }
        }

        return count
    }

    val grid1 = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )
    println(numIslands(grid1)) // 1

    val grid2 = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )
    println(numIslands(grid2)) // 3
}