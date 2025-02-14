package medium

/**
 * Given an integer array nums, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 10
 * - -10 <= nums[i] <= 10
 * - All the numbers of nums are unique.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
fun main() {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val subset = mutableListOf<Int>()

        fun dfs(i: Int) {
            if (i == nums.size) {
                result.add(subset.toList())
                return
            }

            subset.add(nums[i])
            dfs(i + 1)
            subset.removeAt(subset.size - 1)
            dfs(i + 1)
        }

        dfs(0)

        return result
    }

    println(subsets(intArrayOf(1, 2, 3))) // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    println(subsets(intArrayOf(0))) // [[],[0]]
}