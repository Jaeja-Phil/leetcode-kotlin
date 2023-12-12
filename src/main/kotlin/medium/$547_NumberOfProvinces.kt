package medium

import java.util.*

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly
 * connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * constraints:
 * - 1 <= n <= 200
 * - n == isConnected.length
 * - n == isConnected[i].length
 * - isConnected[i][j] is 1 or 0.
 * - isConnected[i][i] == 1
 * - isConnected[i][j] == isConnected[j][i]
 *
 * Example 1:
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 *
 * Example 2:
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 */
fun main() {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        /**
         * create a visited set to keep track of visited cities
         */
        val visited = mutableSetOf<Int>()

        /**
         * create a count variable to keep track of the number of provinces
         */
        var count = 0

        /**
         * iterate through the isConnected matrix
         */
        for (i in isConnected.indices) {
            /**
             * if the city is not visited, increment the count and add the city to the visited set
             */
            if (!visited.contains(i)) {
                count++
                visited.add(i)
            }

            /**
             * create a stack to keep track of the cities that are connected to the current city
             */
            val stack = Stack<Int>()
            stack.push(i)

            /**
             * iterate through the stack until it is empty
             */
            while (stack.isNotEmpty()) {
                /**
                 * pop the city from the stack
                 */
                val currentCity = stack.pop()

                /**
                 * iterate through the cities that are connected to the current city
                 */
                for (j in isConnected[currentCity].indices) {
                    /**
                     * if the city is not visited and the city is connected to the current city, add the city to the
                     * visited set and push the city to the stack
                     */
                    if (!visited.contains(j) && isConnected[currentCity][j] == 1) {
                        visited.add(j)
                        stack.push(j)
                    }
                }
            }
        }

        return count
    }

    val res1 = findCircleNum(arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 1, 0), intArrayOf(0, 0, 1)))
    println(res1) // 2

    val res2 = findCircleNum(arrayOf(intArrayOf(1, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 1)))
    println(res2) // 3
}