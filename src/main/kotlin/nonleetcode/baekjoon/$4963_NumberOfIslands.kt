package nonleetcode.baekjoon

fun main() {
    val solution = `섬의 개수`()
    while (true) {
        val (w, h) = readln().split(" ").map { it.toInt() }
        if (w == 0 && h == 0) return

        val map = Array(h) { readln().split(" ").map { it.toInt() }.toIntArray() }
        println(solution.solve(map))
    }
}

class `섬의 개수` {

    data class Coordinate(
        val x: Int,
        val y: Int
    )

    enum class Direction(val x: Int, val y: Int) {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1), UP_LEFT(-1, -1), UP_RIGHT(-1, 1),
        DOWN_LEFT(1, -1), DOWN_RIGHT(1, 1)
    }

    companion object {
        const val LAND = 1
        const val SEA = 0
    }

    fun solve(map: Array<IntArray>): Int {
        val visited = mutableSetOf<Coordinate>()
        var count = 0
        for (x in map.indices) {
            for (y in map[x].indices) {
                if (map[x][y] == LAND && dfs(x, y, visited, map)) {
                    count++
                }
            }
        }

        return count
    }

    private fun dfs(x: Int, y: Int, visited: MutableSet<Coordinate>, map: Array<IntArray>): Boolean {
        if (visited.add(Coordinate(x, y)).not()) {
            return false
        }

        Direction.values().forEach { direction ->
            val nextCoordinate = Coordinate(x + direction.x, y + direction.y)
            if (isValidCoordinate(nextCoordinate, map)) {
                dfs(nextCoordinate.x, nextCoordinate.y, visited, map)
            }
        }

        return true
    }

    private fun isValidCoordinate(coordinate: Coordinate, map: Array<IntArray>): Boolean {
        return coordinate.x in map.indices && coordinate.y in map[0].indices && map[coordinate.x][coordinate.y] == LAND
    }

}


