package medium

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any
 * order.
 *
 * Constraints:
 * - 1 <= nums.length <= 8
 * - -10 <= nums[i] <= 10
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: [[1,1,2],[1,2,1],[2,1,1]]
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
fun main() {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        val countMap = mutableMapOf<Int, Int>()
        for (num in nums) {
            countMap[num] = countMap.getOrDefault(num, 0) + 1
        }
        val n = nums.size

        fun dfs(perm: MutableList<Int>) {
            if (perm.size == n) {
                res.add(perm.toList())
                return
            }

            for (k in countMap.keys) {
                if (countMap[k]!! > 0) {
                    perm.add(k)
                    countMap[k] = countMap[k]!! - 1
                    dfs(perm)
                    countMap[k] = countMap[k]!! + 1
                    perm.removeLast()
                }
            }
        }

        dfs(mutableListOf())

        return res
    }

    println(permuteUnique(intArrayOf(1, 1, 2))) // [[1,1,2],[1,2,1],[2,1,1]]
//    println(permuteUnique(intArrayOf(1, 2, 3))) // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
}