package medium

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in
 * candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Constraints:
 * - 1 <= candidates.length <= 100
 * - 1 <= candidates[i] <= 50
 * - 1 <= target <= 30
 *
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
 *
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output: [[1,2,2],[5]]
 */
fun main() {
    fun combinationSumII(candidates: IntArray, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        candidates.sort()

        fun dfs(start: Int, total: Int, path: MutableList<Int>) {
            if (total == target) {
                result.add(path.toList())
                return
            }

            if (total > target || start == candidates.size) {
                return
            }

            path.add(candidates[start])
            dfs(start + 1, total + candidates[start], path)
            path.removeAt(path.lastIndex)

            var next = start
            while (next + 1 < candidates.size && candidates[next] == candidates[next + 1]) {
                next++
            }

            dfs(next + 1, total, path)
        }

        dfs(0, 0, mutableListOf())

        return result
    }

    val candidates1 = intArrayOf(10, 1, 2, 7, 6, 1, 5)
    val target1 = 8
    println(combinationSumII(candidates1, target1)) // [[1,1,6],[1,2,5],[1,7],[2,6]]

    val candidates2 = intArrayOf(2, 5, 2, 1, 2)
    val target2 = 5
    println(combinationSumII(candidates2, target2)) // [[1,2,2],[5]]
}