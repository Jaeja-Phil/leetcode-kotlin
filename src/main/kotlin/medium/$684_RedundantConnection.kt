package medium

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The
 * added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is
 * represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes
 * ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers,
 * return the answer that occurs last in the input.
 *
 * Constraints:
 * - n == edges.length
 * - 3 <= n <= 1000
 * - edges[i].length == 2
 * - 1 <= ai < bi <= edges.length
 * - ai != bi
 * - There are no repeated edges.
 * - The given graph is connected.
 *
 * Example 1:
 * Input: edges = [[1, 2], [1, 3], [2, 3]]
 * Output: [2, 3]
 *
 * Example 2:
 * Input: edges = [[1, 2], [2, 3], [3, 4], [1, 4], [1, 5]]
 * Output: [1, 4]
 */
fun main() {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        // Solution 1. dfs
//        val graph = mutableMapOf<Int, MutableSet<Int>>()
//        for (i in 1..edges.size) {
//            graph[i] = mutableSetOf()
//        }
//
//        // check if it is possible to reach the target node from the source node
//        fun dfs(
//            src: Int,
//            target: Int,
//            visited: MutableSet<Int> = mutableSetOf(),
//        ): Boolean {
//            if (src == target) {
//                return true
//            }
//
//            visited.add(src)
//            for (neighbor in graph[src]!!) {
//                if (neighbor !in visited && dfs(neighbor, target, visited)) {
//                    return true
//                }
//            }
//
//            return false
//        }
//
//        for ((src, dest) in edges) {
//            if (dfs(src, dest)) {
//                return intArrayOf(src, dest)
//            }
//
//            graph[src]!!.add(dest)
//            graph[dest]!!.add(src)
//        }
//
//        return intArrayOf()

        // Solution 2. Disjoint Set Union (DSU)
        val n = edges.size
        val parent = IntArray(n + 1) { it }
        val rank = IntArray(n + 1) { 1 }

        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        fun union(x: Int, y: Int): Boolean {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX == rootY) {
                return false
            }
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX
            } else {
                parent[rootY] = rootX
                rank[rootX]++
            }
            return true
        }

        for ((u, v) in edges) {
            if (!union(u, v)) {
                return intArrayOf(u, v)
            }
        }

        return intArrayOf()
    }

    println(
        findRedundantConnection(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3)
            )
        ).contentToString()
    ) // [2, 3]

    println(
        findRedundantConnection(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 4),
                intArrayOf(1, 4),
                intArrayOf(1, 5)
            )
        ).contentToString()
    ) // [1, 4]

    println(
        findRedundantConnection(
            arrayOf(
                intArrayOf(2, 7),
                intArrayOf(7, 8),
                intArrayOf(3, 6),
                intArrayOf(2, 5),
                intArrayOf(6, 8),
                intArrayOf(4, 8),
                intArrayOf(2, 8),
                intArrayOf(1, 8),
                intArrayOf(7, 10),
                intArrayOf(3, 9)
            )
        ).contentToString()
    ) // [2, 8]
}