package medium

import java.util.PriorityQueue

/**
 * There are n cities connected by some number of flights. You are given an array flights where
 * flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
 * If there is no such route, return -1.
 *
 * Constraints:
 * - 1 <= n <= 100
 * - 0 <= flights.length <= (n * (n - 1) / 2)
 * - flights[i].length == 3
 * - 0 <= fromi, toi < n
 * - fromi != toi
 * - 1 <= pricei <= 10^4
 * - There will not be any duplicated flights or self cycles.
 * - 0 <= src, dst, k < n
 * - src != dst
 *
 * Example 1:
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 */
fun main() {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        // Bellman-Ford algorithm
        val prices = IntArray(n) { Int.MAX_VALUE }
        prices[src] = 0

        for (i in 0..k) {
            val temp = prices.copyOf()
            for ((from, to, price) in flights) {
                if (prices[from] != Int.MAX_VALUE && prices[from] + price < temp[to]) {
                    temp[to] = prices[from] + price
                }
            }

            temp.copyInto(prices)
        }

        return if (prices[dst] == Int.MAX_VALUE) -1 else prices[dst]
    }

    println(
        findCheapestPrice(
            4,
            arrayOf(intArrayOf(0, 1, 100), intArrayOf(1, 2, 100), intArrayOf(2, 0, 100), intArrayOf(1, 3, 600), intArrayOf(2, 3, 200)),
            0,
            3,
            1
        )
    ) // 700

}