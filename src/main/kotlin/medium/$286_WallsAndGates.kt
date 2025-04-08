package medium

import java.util.*

/**
 * You are given a m x n 2D grid initialized with these three possible values:
 * - -1 - A water cell that can not be traversed.
 * - 0 - A treasure chest.
 * - INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
 * Fill each land cell with the distance to its nearest treasure chest.
 * If a land cell cannot reach a treasure chest than the value should remain INF.
 *
 * Assume the grid can only be traversed up, down, left, or right.
 *
 * Modify the grid in-place.
 *
 * Constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 100
 * - grid[i][j] is one of {-1, 0, 2147483647}
 *
 * Example 1:
 * Input: [
 *   [2147483647,-1,0,2147483647],
 *   [2147483647,2147483647,2147483647,-1],
 *   [2147483647,-1,2147483647,-1],
 *   [0,-1,2147483647,2147483647]
 * ]
 * Output: [
 *   [3,-1,0,1],
 *   [2,2,1,-1],
 *   [1,-1,2,-1],
 *   [0,-1,3,4]
 * ]
 */
fun main() {
    fun islandsAndTreasure(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size
        val directions = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(-1, 0)
        )
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == 0) {
                    queue.offer(Pair(i, j))
                }
            }
        }

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            for (dir in directions) {
                val newX = x + dir[0]
                val newY = y + dir[1]
                if (newX in 0..<m && newY in 0..<n && grid[newX][newY] == Int.MAX_VALUE) {
                    grid[newX][newY] = grid[x][y] + 1
                    queue.offer(Pair(newX, newY))
                }
            }
        }

        return grid
    }

    // Test case
    val grid = arrayOf(
        intArrayOf(Int.MAX_VALUE, -1, 0, Int.MAX_VALUE),
        intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, -1),
        intArrayOf(Int.MAX_VALUE, -1, Int.MAX_VALUE, -1),
        intArrayOf(0, -1, Int.MAX_VALUE, Int.MAX_VALUE)
    )
    val result = islandsAndTreasure(grid)
    for (row in result) {
        println(row.joinToString(", "))
    }
    // Output:
    // [3, -1, 0, 1]
    // [2, 2, 1, -1]
    // [1, -1, 2, -1]
    // [0, -1, 3, 4]
}