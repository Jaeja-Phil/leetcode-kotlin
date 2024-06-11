package nonleetcode.baekjoon

import kotlin.math.abs

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }
    println(`$15686_치킨배달`().solve(map, m))
}

class `$15686_치킨배달` {
    data class Point(val x: Int, val y: Int)

    fun solve(map: Array<IntArray>, maxChickenStores: Int): Int {
        // find the location of houses & chicken stores
        val houses = mutableListOf<Point>()
        val chickenStores = mutableListOf<Point>()
        map.forEachIndexed { i, row ->
            row.forEachIndexed { j, value ->
                when (value) {
                    1 -> houses.add(Point(i, j))
                    2 -> chickenStores.add(Point(i, j))
                }
            }
        }

        var minDistance = Int.MAX_VALUE
        fun dfs(idx: Int, selected: MutableList<Point>) {
            if (idx == chickenStores.size || selected.size == maxChickenStores) {
                if (selected.size == 0) return

                var sum = 0
                houses.forEach { house ->
                    var distance = Int.MAX_VALUE
                    selected.forEach { chickenStore ->
                        distance = minOf(distance, calculateDistance(house, chickenStore))
                    }
                    sum += distance
                }
                minDistance = minOf(minDistance, sum)
                return
            }

            // recursive case
            selected.add(chickenStores[idx])
            dfs(idx + 1, selected)
            selected.removeLast()
            dfs(idx + 1, selected)
        }

        dfs(0, mutableListOf())

        return minDistance
    }

    private fun calculateDistance(p1: Point, p2: Point): Int {
        return abs(p1.x - p2.x) + abs(p1.y - p2.y)
    }
}