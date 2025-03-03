package medium

/**
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is
 * an edge between node a and node b in the graph.
 *
 * The nodes are numbered from 0 to n - 1.
 *
 * Return the total number of connected components in that graph.
 *
 * Constraints:
 * - 1 <= n <= 100
 * - 0 <= edges.length <= n * (n - 1) / 2
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2]]
 * Output: 1
 *
 * Example 2:
 * Input: n = 6, edges = [[0,1],[1,2],[2,3],[4,5]]
 * Output: 2
 */
fun main() {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0..<n) {
            graph[i] = mutableSetOf()
        }
        edges.forEach { (a, b) ->
            graph[a]!!.add(b)
            graph[b]!!.add(a)
        }

        val visited = mutableSetOf<Int>()
        fun dfs(node: Int) {
            visited.add(node)
            for (neighbor in graph[node]!!) {
                if (neighbor in visited) {
                    continue
                }
                dfs(neighbor)
            }
        }

        var count = 0
        for (i in 0..<n) {
            if (i !in visited) {
                dfs(i)
                count++
            }
        }

        return count
    }

    println(countComponents(3, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)))) // 1
    println(countComponents(6, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(4, 5)))) // 2
}