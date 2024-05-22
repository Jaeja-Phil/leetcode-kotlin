package nonleetcode.baekjoon

fun main() {
    val solution = `상범빌딩`()
    while (true) {
        val (l, r, c) = readlnOrNull()?.split(" ")?.map { it.toInt() } ?: break
        if (l == 0 && r == 0 && c == 0) break
        val map = Array(l) { Array(r) { CharArray(c) } }
        var sLocation = `상범빌딩`.Location(0, 0, 0)
        var eLocation = `상범빌딩`.Location(0, 0, 0)
        for (i in 0 until l) {
            for (j in 0 until r) {
                val row = readln().toCharArray()
                map[i][j] = row
                row.forEachIndexed { index, ch ->
                    when (ch) {
                        'S' -> sLocation = `상범빌딩`.Location(i, j, index)
                        'E' -> eLocation = `상범빌딩`.Location(i, j, index)
                    }
                }
            }
            readln() // Read the empty line between floors
        }
        val res = solution.solve(map, sLocation, eLocation)
        if (res >= 0) {
            println("Escaped in $res minute(s).")
        } else {
            println("Trapped!")
        }
    }
}

class `상범빌딩` {

    data class Location(val floor: Int, val row: Int, val col: Int)

    enum class Direction(val floor: Int, val row: Int, val col: Int) {
        FLOOR_UP(1, 0, 0),
        FLOOR_DOWN(-1, 0, 0),
        NORTH(0, -1, 0),
        SOUTH(0, 1, 0),
        EAST(0, 0, 1),
        WEST(0, 0, -1);
    }

    fun solve(buildingMap: Array<Array<CharArray>>, sLocation: Location, eLocation: Location): Int {
        val directions = Direction.values()
        val visited = mutableSetOf<Location>()
        val queue = ArrayDeque<Pair<Location, Int>>()
        queue.add(sLocation to 0)
        visited.add(sLocation)

        while (queue.isNotEmpty()) {
            val (currentLocation, time) = queue.removeFirst()
            if (currentLocation == eLocation) {
                return time
            }

            for (direction in directions) {
                val nextLocation = Location(
                    currentLocation.floor + direction.floor,
                    currentLocation.row + direction.row,
                    currentLocation.col + direction.col
                )
                if (isValidLocation(nextLocation, buildingMap, visited)) {
                    queue.add(nextLocation to time + 1)
                    visited.add(nextLocation)
                }
            }
        }

        return -1
    }

    private fun isValidLocation(
        location: Location,
        buildingMap: Array<Array<CharArray>>,
        visited: MutableSet<Location>
    ): Boolean {
        val (floor, row, col) = location
        if (floor !in buildingMap.indices ||
            row !in buildingMap[floor].indices ||
            col !in buildingMap[floor][row].indices
        ) {
            return false
        }

        if (buildingMap[floor][row][col] == '#' || location in visited) {
            return false
        }

        return true
    }
}
