package medium

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean
 * touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where
 * heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and
 * west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any
 * cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell
 * (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * Constraints:
 * - m == heights.length
 * - n == heights[r].length
 * - 1 <= m, n <= 200
 * - 0 <= heights[r][c] <= 10^5
 *
 * Example 1:
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * Example 2:
 * Input: heights = [[1]]
 * Output: [[0,0]]
 */
fun main() {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val pacific = mutableSetOf<Pair<Int, Int>>() // (x,y) coordinates of cells that can flow to the Pacific
        val atlantic = mutableSetOf<Pair<Int, Int>>() // (x,y) coordinates of cells that can flow to the Atlantic

        fun dfs(x: Int, y: Int, ocean: MutableSet<Pair<Int, Int>>) {
            if (x to y in ocean) return
            ocean.add(x to y)

            val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
            for ((dx, dy) in directions) {
                val newX = x + dx
                val newY = y + dy
                if (newX !in heights.indices || newY !in heights[x].indices || heights[newX][newY] < heights[x][y]) {
                    continue
                }
                dfs(newX, newY, ocean)
            }
        }

        for (i in heights.indices) {
            dfs(i, 0, pacific)
            dfs(i, heights[i].size - 1, atlantic)
        }

        for (j in heights[0].indices) {
            dfs(0, j, pacific)
            dfs(heights.size - 1, j, atlantic)
        }

        val result = mutableListOf<List<Int>>()
        for (i in heights.indices) {
            for (j in heights[i].indices) {
                if (i to j in pacific && i to j in atlantic) {
                    result.add(listOf(i, j))
                }
            }
        }
        return result
    }

    val heights = arrayOf(
        intArrayOf(1, 2, 2, 3, 5),
        intArrayOf(3, 2, 3, 4, 4),
        intArrayOf(2, 4, 5, 3, 1),
        intArrayOf(6, 7, 1, 4, 5),
        intArrayOf(5, 1, 1, 2, 4)
    )
    println(pacificAtlantic(heights)) // [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

    val heights2 = arrayOf(intArrayOf(1))
    println(pacificAtlantic(heights2)) // [[0, 0]]
}
