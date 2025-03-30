package medium

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any
 * order.
 *
 * Constraints:
 * - 1 <= nums.length <= 6
 * - -10 <= nums[i] <= 10
 * - All integers of nums are unique.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 * Input: nums = [1]
 * Output: [[1]]
 */
fun main() {
    fun permute(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val visitedIndexes = mutableSetOf<Int>()
        val n = nums.size

        fun dfs(perm: MutableList<Int>) {
            if (perm.size == n) {
                res.add(perm.toList())
                return
            }

            for (i in nums.indices) {
                if (i !in visitedIndexes) {
                    visitedIndexes.add(i)
                    perm.add(nums[i])
                    dfs(perm)
                    perm.removeLast()
                    visitedIndexes.remove(i)
                }
            }
        }

        dfs(mutableListOf())

        return res
    }

    println(permute(intArrayOf(1, 2, 3))) // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    println(permute(intArrayOf(0, 1))) // [[0,1],[1,0]]
    println(permute(intArrayOf(1))) // [[1]]
}