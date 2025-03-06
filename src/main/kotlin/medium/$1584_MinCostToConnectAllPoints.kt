package medium

import java.util.*
import kotlin.math.abs

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where
 * points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them:
 * |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path
 * between any two points.
 *
 * Constraints:
 * - 1 <= points.length <= 1000
 * - -10^6 <= xi, yi <= 10^6
 * - All pairs (xi, yi) are distinct.
 *
 * Example 1:
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 *
 * Example 2:
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 */
fun main() {
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        val graph = mutableMapOf<Int, MutableSet<Pair<Int, Int>>>()

        for (i in 0..<n) {
            val (x1, y1) = points[i]
            for (j in i + 1..<n) {
                val (x2, y2) = points[j]
                val distance = abs(x1 - x2) + abs(y1 - y2)
                graph.getOrPut(i) { mutableSetOf() }.add(j to distance)
                graph.getOrPut(j) { mutableSetOf() }.add(i to distance)
            }
        }

        val visited = mutableSetOf<Int>()
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        minHeap.add(0 to 0)
        var cost = 0

        while (visited.size < n) {
            val (node, distance) = minHeap.poll()
            if (node in visited) continue

            visited.add(node)
            cost += distance
            for ((neighbor, dist) in graph[node] ?: emptySet()) {
                if (neighbor !in visited) {
                    minHeap.add(neighbor to dist)
                }
            }
        }

        return cost
    }

    println(
        minCostConnectPoints(
            arrayOf(
                intArrayOf(0, 0),
                intArrayOf(2, 2),
                intArrayOf(3, 10),
                intArrayOf(5, 2),
                intArrayOf(7, 0)
            )
        )
    ) // 20

//    println(
//        minCostConnectPoints(
//            arrayOf(
//                intArrayOf(3, 12),
//                intArrayOf(-2, 5),
//                intArrayOf(-4, 1)
//            )
//        )
//    ) // 18
}