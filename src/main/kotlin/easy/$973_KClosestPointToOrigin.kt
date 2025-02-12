package easy

import java.util.*

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the
 * k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * Constraints:
 * - 1 <= k <= points.length <= 10^4
 * - -10^4 < xi, yi < 10^4
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 */
fun main() {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val minHeap = PriorityQueue(compareBy<IntArray> { it[0] * it[0] + it[1] * it[1] })

        for (point in points) {
            minHeap.add(point)
        }

        val res = mutableListOf<IntArray>()
        repeat(k) {
            res.add(minHeap.poll())
        }

        return res.toTypedArray()
    }

    println(kClosest(arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2)), 1).contentDeepToString()) // [[-2, 2]]
    println(kClosest(arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4)), 2).contentDeepToString()) // [[3, 3], [-2, 4]]
}