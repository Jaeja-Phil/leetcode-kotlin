package medium

import java.util.*

/**
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
 * for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and
 * that there is no contradiction.
 *
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for
 * them.
 *
 * constraints:
 * - 1 <= equations.length <= 20
 * - equations[i].length == 2
 * - 1 <= Ai.length, Bi.length <= 5
 * - values.length == equations.length
 * - 0.0 < values[i] <= 20.0
 * - 1 <= queries.length <= 20
 * - queries[i].length == 2
 * - 1 <= Cj.length, Dj.length <= 5
 * - Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 */
fun main() {
    /**
     * create a data class to represent the edge
     * - s: character representing the edge
     * - v: value of the edge
     * ex) a / b = 2.0 --> Edge("b", 2.0) is added to the list of edges for "a"
     */
    data class Edge(val s: String, val v: Double)

    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        /**
         * create a map to keep track of the edges
         * - key: character representing the edge
         * - value: list of edges
         */
        val fromTo = mutableMapOf<String,MutableList<Edge>>()

        /**
         * iterate through the equations and add the edges to the map
         * -
         */
        equations.forEachIndexed{ i, e ->
            /**
             * each element in the equations list is a list of two elements
             * - first: numerator
             * - second: denominator
             * for each element, add the edge to the map
             *
             * ex) a / b = 2.0
             * - Edge("b", 2.0) is added to the list of edges for "a"
             * - Edge("a", 0.5) is added to the list of edges for "b" (1/2 = 0.5)
             *
             * **note: this is a directed graph, so we need to add the edge for both directions
             */
            fromTo.getOrPut(e[0]) { mutableListOf() }.add(Edge(e[1], values[i]))
            fromTo.getOrPut(e[1]) { mutableListOf() }.add(Edge(e[0], 1 / values[i]))
        }

        /**
         * iterate through the queries and find the value for each query
         */
        return DoubleArray(queries.size){ i ->
            /**
             * create
             * - a queue for BFS
             * - a set to keep track of visited nodes
             */
            val queue = ArrayDeque<Edge>()
            val visited = mutableSetOf<String>()

            /**
             * add current query to the queue with initial value of 1.0 (a / a = 1.0)
             */
            queue.add(Edge(queries[i][0],1.0))

            /**
             * start BFS
             */
            while(queue.isNotEmpty()) {
                /**
                 * for the current level of BFS, iterate through the queue
                 */
                repeat(queue.size) {
                    /**
                     * pop the edge from the queue
                     */
                    val currentEdge = queue.removeFirst()

                    /**
                     * if current edge has no mapped edges, return -1.0
                     */
                    val adjacentEdges = fromTo[currentEdge.s] ?: return@DoubleArray -1.0

                    /**
                     * if current edge matches the requested query, return the value
                     */
                    if (currentEdge.s == queries[i][1]) return@DoubleArray currentEdge.v

                    /**
                     * iterate through the adjacent edges
                     * - first check if the adjacent edge is not visited (and add it to the visited set while doing so)
                     * - then all first-visited adjacent edges will repopulate the queue
                     *   - here, we need to multiply the value of the current edge and the adjacent edge to correctly maintain the ratio between characters
                     */
                    adjacentEdges.asSequence()
                        .filter{ visited.add(it.s) }
                        .map{ Edge(it.s, currentEdge.v * it.v) }
                        .toCollection(queue)
                }
            }

            /**
             * if above BFS does not return the value, return -1.0
             */
            -1.0
        }
    }

    val equations1 = listOf(listOf("a", "b"), listOf("b", "c"))
    val values1 = doubleArrayOf(2.0, 3.0)
    val queries1 = listOf(listOf("a", "c"), listOf("b", "a"), listOf("a", "e"), listOf("a", "a"), listOf("x", "x"))
    println(calcEquation(equations1, values1, queries1).contentToString()) // [6.00000,0.50000,-1.00000,1.00000,-1.00000]

    val equations2 = listOf(listOf("a", "b"), listOf("b", "c"), listOf("bc", "cd"))
    val values2 = doubleArrayOf(1.5, 2.5, 5.0)
    val queries2 = listOf(listOf("a", "c"), listOf("c", "b"), listOf("bc", "cd"), listOf("cd", "bc"))
    println(calcEquation(equations2, values2, queries2).contentToString()) // [3.75000,0.40000,5.00000,0.20000]
}
