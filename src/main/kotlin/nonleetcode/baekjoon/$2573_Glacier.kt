package nonleetcode.baekjoon

fun main() {
    val (row, col) = readln().split(" ").map { it.toInt() }
    val grid = Array(row) { readln().split(" ").map { it.toInt() }.toIntArray() }
    println(`빙산`().solve(grid))
}

class `빙산` {
    companion object {
        private const val SEA = 0
    }

    data class Point(val x: Int, val y: Int)

    enum class Direction(val x: Int, val y: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
    }

    fun solve(grid: Array<IntArray>): Int {
        val glacierPoints = mutableSetOf<Point>()
        for (i in 1..<grid.lastIndex) {
            for (j in 1..<grid[i].lastIndex) {
                if (grid[i][j] != 0) {
                    glacierPoints.add(Point(i, j))
                }
            }
        }

        var years = 0
        while (glacierPoints.isNotEmpty()) {
            if (isGlacierConnected(glacierPoints).not()) {
                return years
            }
            years++
            meltGlacier(grid, glacierPoints)
        }

        return 0
    }

    private fun isGlacierConnected(glacierPoints: Set<Point>): Boolean {
        val visited = mutableSetOf<Point>()
        val queue = ArrayDeque<Point>()
        queue.add(glacierPoints.first())
        visited.add(glacierPoints.first())

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (direction in Direction.entries) {
                val next = Point(current.x + direction.x, current.y + direction.y)
                if (next in glacierPoints && next !in visited) {
                    queue.add(next)
                    visited.add(next)
                }
            }
        }

        return visited.size == glacierPoints.size
    }

    private fun meltGlacier(grid: Array<IntArray>, glacierPoints: MutableSet<Point>) {
        val meltInfo = mutableListOf<Pair<Point, Int>>()
        for (point in glacierPoints) {
            val adjacentSeaCount = adjacentSeaCount(point, grid)
            meltInfo.add(point to adjacentSeaCount)
        }
        meltInfo.forEach { (point, meltCount) ->
            grid[point.x][point.y] = maxOf(0, grid[point.x][point.y] - meltCount)
            if (grid[point.x][point.y] == 0) {
                glacierPoints.remove(point)
            }
        }
    }

    private fun adjacentSeaCount(point: Point, grid: Array<IntArray>): Int {
        var count = 0
        for (direction in Direction.entries) {
            val next = Point(point.x + direction.x, point.y + direction.y)
            if (grid[next.x][next.y] == SEA) {
                count++
            }
        }
        return count
    }
}
