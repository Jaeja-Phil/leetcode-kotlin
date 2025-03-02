package medium

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 *
 * Example 1:
 * Input: n = 5, and edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * Output: false
 */
fun main() {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 0..<n) {
            graph[i] = mutableSetOf()
        }
        edges.forEach { (a, b) ->
            graph[a]!!.add(b)
            graph[b]!!.add(a)
        }

        val visited = mutableSetOf<Int>()

        fun dfs(node: Int, parent: Int): Boolean {
            // if the node has been visited, then there is a cycle
            if (visited.contains(node)) {
                return false
            }

            visited.add(node)
            for (neighbor in graph[node]!!) {
                // skip the parent node since this is an undirected graph
                if (neighbor == parent) {
                    continue
                }

                // make recursive call to the neighbor node
                if (!dfs(neighbor, node)) {
                    return false
                }
            }
            return true
        }

        return dfs(0, -1) && visited.size == n
    }

    println(
        validTree(5, arrayOf(intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3), intArrayOf(1, 4)))
    ) // true

    println(
        validTree(5, arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(1, 3), intArrayOf(1, 4)))
    ) // false
}
