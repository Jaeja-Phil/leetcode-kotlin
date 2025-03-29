package medium

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 * Constraints:
 * - 1 <= n <= 20
 * - 1 <= k <= n
 *
 * Example 1:
 * Input: n = 4, k = 2
 * Output: [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: [[1]]
 */
fun main() {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        fun dfs(index: Int, arr: MutableList<Int>) {
            if (index > n + 1) return
            if (arr.size == k) {
                res.add(arr.toList())
                return
            }

            arr.add(index)
            dfs(index + 1, arr)
            arr.removeLast()
            dfs(index + 1, arr)
        }

        dfs(1, mutableListOf())

        return res
    }

    println(combine(4, 2)) // [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
    println(combine(1, 1)) // [[1]]
}