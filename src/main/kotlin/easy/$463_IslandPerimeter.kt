package easy

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents
 * water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and
 * there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell
 * is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter
 * of the island.
 *
 * Constraints:
 * - row == grid.length
 * - col == grid[i].length
 * - 1 <= row, col <= 100
 * - grid[i][j] is 0 or 1.
 * - There is exactly one island in grid.
 *
 * Example 1:
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 *
 * Example 2:
 * Input: grid = [[1]]
 * Output: 4
 *
 * Example 3:
 * Input: grid = [[1,0]]
 * Output: 4
 */
fun main() {
    fun islandPerimeter(grid: Array<IntArray>): Int {
        val directions = arrayOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        var perimeter = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    for ((dx, dy) in directions) {
                        val x = i + dx
                        val y = j + dy
                        if (x < 0 || x >= grid.size || y < 0 || y >= grid[i].size || grid[x][y] == 0) {
                            perimeter++
                        }
                    }
                }
            }
        }

        return perimeter
    }

    println(
        islandPerimeter(
            arrayOf(
                intArrayOf(0, 1, 0, 0),
                intArrayOf(1, 1, 1, 0),
                intArrayOf(0, 1, 0, 0),
                intArrayOf(1, 1, 0, 0)
            )
        )
    ) // 16
    println(islandPerimeter(arrayOf(intArrayOf(1)))) // 4
    println(islandPerimeter(arrayOf(intArrayOf(1, 0)))) // 4
}