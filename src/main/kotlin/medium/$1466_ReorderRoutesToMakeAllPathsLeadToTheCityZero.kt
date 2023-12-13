package medium

/**
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between
 * two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads
 * in one direction because they are too narrow.
 *
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 *
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 *
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of
 * edges changed.
 *
 * It's guaranteed that each city can reach city 0 after reorder.
 *
 * constraints:
 * - 2 <= n <= 5 * 10^4
 * - connections.length == n - 1
 * - connections[i].length == 2
 * - 0 <= ai, bi <= n - 1
 * - ai != bi
 *
 * Example 1:
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 2:
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 3:
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 *
 */
fun main() {
    data class Road(val to: Int, val reversed: Boolean)

    fun minReorder(n: Int, connections: Array<IntArray>): Int {
        /**
         * create an array of lists to keep track of the roads
         * - each index represents a city
         * - each index contains a list of roads that are connected to the city
         * - each road contains the city that it is connected to and whether it is reversed or not
         */
        val roads = Array(n) { ArrayList<Road>() }

        /**
         * populate roads array with given connections
         * - each connection is a pair of cities (from, to)
         * - add a road from the "from" city to the "to" city and mark it as reversed
         * - add a road from the "to" city to the "from" city and mark it as not reversed
         *
         * why is from --> to marked as reversed?
         * - because we will start from city 0 and traverse the roads
         */
        connections.forEach { (from, to) ->
            roads[from].add(Road(to, true))
            roads[to].add(Road(from, false))
        }

        /**
         * create a visited array to keep track of visited cities
         */
        val visited = BooleanArray(n)

        /**
         * create a variable to keep track of the number of reversed roads (this will eventually be the answer)
         */
        var reversed = 0

        /**
         * create a queue for BFS traversal, starting from city 0
         */
        val queue = ArrayDeque<Int>()
        queue.add(0) // starting from city 0 part

        /**
         * perform BFS traversal
         */
        while (queue.isNotEmpty()) {
            /**
             * check the size of current queue (which will hold the cities that are connected to the current city)
             */
            val size = queue.size
            /**
             * consume current queue
             */
            repeat(size) {
                val nextCity = queue.removeFirst()
                /**
                 * if the city has been visited, it means that we have already traversed the roads from this city
                 * so skip it.
                 * if the city has not been visited, mark it as visited and add the roads that are connected to this
                 */
                if (visited[nextCity]) return@repeat
                visited[nextCity] = true

                /**
                 * put the roads that are connected to the current city into the queue (if the city has not been visited)
                 * while doing so, increment the reversed variable if the road is reversed
                 */
                roads[nextCity].forEach { road ->
                    if (!visited[road.to]) {
                        queue.add(road.to)
                        if (road.reversed) reversed++
                    }
                }
            }
        }

        return reversed
    }

    val res1 = minReorder(6, arrayOf(intArrayOf(0,1), intArrayOf(1,3), intArrayOf(2,3), intArrayOf(4,0), intArrayOf(4,5)))
    println(res1) // 3

    val res2 = minReorder(5, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(3,2), intArrayOf(3,4)))
    println(res2) // 2

    val res3 = minReorder(3, arrayOf(intArrayOf(1,0), intArrayOf(2,0)))
    println(res3) // 0
}