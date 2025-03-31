package medium

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * Constraints:
 * - 1 <= nums.length <= 10
 * - -10 <= nums[i] <= 10
 *
 * Example 1:
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 */
fun main() {
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        nums.sort()
        val n = nums.size

        fun dfs(start: Int, path: MutableList<Int>) {
            if (start == n) {
                res.add(path.toList())
                return
            }

            path.add(nums[start])
            dfs(start + 1, path)
            path.removeLast()

            var nextStart = start + 1
            while (nextStart < n && nums[nextStart] == nums[nextStart - 1]) {
                nextStart++
            }

            dfs(nextStart, path)
        }

        dfs(0, mutableListOf())

        return res
    }

    println(subsetsWithDup(intArrayOf(1, 2, 2))) // [[],[1],[1,2],[1,2,2],[2],[2,2]]
    println(subsetsWithDup(intArrayOf(0))) // [[],[0]]
}