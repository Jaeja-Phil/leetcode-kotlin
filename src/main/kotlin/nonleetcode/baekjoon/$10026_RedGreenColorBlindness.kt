package nonleetcode.baekjoon

fun main() {
    val n = readln().toInt()
    val grid = Array(n) { readln().toCharArray() }
    val res = `적록색약`().solve(grid)
    println("${res.first} ${res.second}")
}

class `적록색약` {

    data class Cell(val x: Int, val y: Int)

    enum class Color {
        R, G, B;

        fun isSameColor(other: Color, isColorBlind: Boolean): Boolean {
            if (isColorBlind) {
                return this == other || (this == R && other == G) || (this == G && other == R)
            }
            return this == other
        }
    }

    enum class Direction(val x: Int, val y: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1)
    }

    fun solve(grid: Array<CharArray>): Pair<Int, Int> {
        var normal = 0
        var colorBlind = 0
        val normalVisited = mutableSetOf<Cell>()
        val colorBlindVisited = mutableSetOf<Cell>()
        val rowSize = grid.size
        val colSize = grid[0].size
        repeat(rowSize) { row ->
            repeat(colSize) { col ->
                val cell = Cell(row, col)
                if (cell !in normalVisited) {
                    bfs(grid, normalVisited, cell, false)
                    normal++
                }
                if (cell !in colorBlindVisited) {
                    bfs(grid, colorBlindVisited, cell, true)
                    colorBlind++
                }
            }
        }

        return Pair(normal, colorBlind)
    }

    fun bfs(grid: Array<CharArray>, visited: MutableSet<Cell>, start: Cell, isColorBlind: Boolean) {
        if (start in visited) return

        val queue = mutableListOf<Cell>()
        queue.add(start)
        visited.add(start)

        while (queue.isNotEmpty()) {
            val current = queue.removeAt(0)
            val currentColor = Color.valueOf(grid[current.x][current.y].toString())

            for (direction in Direction.entries) {
                val nextX = current.x + direction.x
                val nextY = current.y + direction.y

                if (nextX in grid.indices && nextY in grid[0].indices) {
                    val nextColor = Color.valueOf(grid[nextX][nextY].toString())
                    if (nextColor.isSameColor(currentColor, isColorBlind)) {
                        val nextCell = Cell(nextX, nextY)
                        if (nextCell !in visited) {
                            queue.add(nextCell)
                            visited.add(nextCell)
                        }
                    }
                }
            }
        }
    }
}