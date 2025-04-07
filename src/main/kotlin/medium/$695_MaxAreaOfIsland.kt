package medium

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 *
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 50
 * - grid[i][j] is either 0 or 1.
 *
 * Example 1:
 * Input: grid = [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 *
 * Example 2:
 * Input: grid = [
 * [0,0,0,0,0,0,0,0]
 * ]
 * Output: 0
 */
fun main() {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        // Solution 1. DFS
//        val visited = mutableSetOf<Pair<Int, Int>>()
//        var maxArea = 0
//
//        fun dfs(x: Int, y: Int): Int {
//            if (x !in grid.indices || y !in grid[x].indices || grid[x][y] == 0 || x to y in visited) {
//                return 0
//            }
//
//            visited.add(x to y)
//            var ans = 1 // to account for the current cell
//            ans += dfs(x + 1, y) // down
//            ans += dfs(x - 1, y) // up
//            ans += dfs(x, y + 1) // right
//            ans += dfs(x, y - 1) // left
//
//            return ans
//        }
//
//        repeat(grid.size) { rowIdx ->
//            repeat(grid[rowIdx].size) { colIdx ->
//                if (grid[rowIdx][colIdx] == 1 && rowIdx to colIdx !in visited) {
//                    maxArea = maxOf(maxArea, dfs(rowIdx, colIdx))
//                }
//            }
//        }
//
//        return maxArea

        // Solution 2. Disjoint Set Union (DSU)
        val rows = grid.size
        val cols = grid[0].size
        val parent = IntArray(rows * cols) { it }
        val size = IntArray(rows * cols) { 1 }
        val directions = arrayOf(
            intArrayOf(0, 1), // right
            intArrayOf(1, 0), // down
            intArrayOf(0, -1), // left
            intArrayOf(-1, 0) // up
        )

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX != rootY) {
                if (size[rootX] < size[rootY]) {
                    parent[rootX] = rootY
                    size[rootY] += size[rootX]
                } else {
                    parent[rootY] = rootX
                    size[rootX] += size[rootY]
                }
            }
        }

        for (i in 0 ..< rows) {
            for (j in 0 ..< cols) {
                if (grid[i][j] == 1) {
                    for (dir in directions) {
                        val newRow = i + dir[0]
                        val newCol = j + dir[1]
                        if (newRow in 0 ..< rows && newCol in 0 ..< cols && grid[newRow][newCol] == 1) {
                            union(i * cols + j, newRow * cols + newCol)
                        }
                    }
                }
            }
        }

        var maxArea = 0
        for (i in 0 ..< rows) {
            for (j in 0 ..< cols) {
                if (grid[i][j] == 1) {
                    val root = find(i * cols + j)
                    maxArea = maxOf(maxArea, size[root])
                }
            }
        }

        return maxArea
    }

    val grid1 = arrayOf(
        intArrayOf(0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
        intArrayOf(0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0),
        intArrayOf(0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0)
    )
    println(maxAreaOfIsland(grid1)) // 6

    val grid2 = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0)
    )
    println(maxAreaOfIsland(grid2)) // 0
}