package medium

import kotlin.math.min

/**
 * You are given an m x n grid where each cell can have one of three values:
 * - 0 representing an empty cell,
 * - 1 representing a fresh orange, or
 * - 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible,
 * return -1.
 *
 * constraints:
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 10
 * - grid[i][j] is 0, 1, or 2.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 */
fun main() {
    fun orangesRotting(grid: Array<IntArray>): Int {
        /**
         * create a queue to keep track of the rotten oranges
         */
        val queue = ArrayDeque<Pair<Int, Int>>()

        /**
         * create a variable to keep track of the number of fresh oranges
         */
        var freshOrangesCount = 0

        /**
         * iterate through the grid and
         * - add the rotten oranges to the queue
         * - count the number of fresh oranges
         */
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, cell ->
                if (cell == 2) {
                    queue.add(Pair(i, j))
                } else if (cell == 1) {
                    freshOrangesCount++
                }
            }
        }

        /**
         * if there are no fresh oranges, return 0
         */
        if (freshOrangesCount == 0) return 0

        /**
         * create a variable to keep track of the number of minutes
         */
        var minutes = 0

        /**
         * create a directions array to keep track of the directions to move
         */
        val directions = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

        /**
         * iterate through the queue until it is empty
         */
        while (queue.isNotEmpty()) {
            val size = queue.size
            repeat(size) {
                val currentOrange = queue.removeFirst()
                directions.forEach { direction ->
                    val nextRow = currentOrange.first + direction.first
                    val nextCol = currentOrange.second + direction.second

                    /**
                     * if the next cell is within the grid and the next cell is a fresh orange,
                     * - decrement the fresh orange count
                     * - add the next cell to the queue
                     * - change the next cell to a rotten orange
                     */
                    if (nextRow in grid.indices &&
                        nextCol in grid[0].indices &&
                        grid[nextRow][nextCol] == 1) {
                        freshOrangesCount--
                        queue.add(Pair(nextRow, nextCol))
                        grid[nextRow][nextCol] = 2
                    }
                }
            }

            /**
             * increment the number of minutes
             */
            minutes++

            /**
             * if there are no fresh oranges, return the number of minutes
             */
            if (freshOrangesCount == 0) return minutes
        }

        return if (freshOrangesCount == 0) minutes else -1
    }

    val grid1 = arrayOf(intArrayOf(2,1,1), intArrayOf(1,1,0), intArrayOf(0,1,1))
    val grid2 = arrayOf(intArrayOf(2,1,1), intArrayOf(0,1,1), intArrayOf(1,0,1))
    val grid3 = arrayOf(intArrayOf(0,2))
    println(orangesRotting(grid1)) // 4
    println(orangesRotting(grid2)) // -1
    println(orangesRotting(grid3)) // 0
}