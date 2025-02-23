package medium

/**
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and
 * then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the
 * expression "+2-1".
 *
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * Constraints:
 * - 1 <= nums.length <= 20
 * - 0 <= nums[i] <= 1000
 * - 0 <= sum(nums[i]) <= 1000
 * - -1000 <= target <= 1000
 *
 * Example 1:
 * Input: nums = [1, 1, 1, 1, 1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 */
fun main() {
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        // Recursive Solution
//        val dp = mutableMapOf<Pair<Int,Int>, Int>() // index, current sum, count
//
//        fun dfs(index: Int, total: Int): Int {
//            // base case
//            if (index >= nums.size) {
//                return when {
//                    total == target -> 1
//                    else -> 0
//                }
//            }
//            // return from cache if computed
//            dp[index to total]?.let { return it }
//
//            // compute & cache
//            dp[index to total] = dfs(index + 1, total + nums[index]) + dfs(index + 1, total - nums[index])
//
//            return dp[index to total]!!
//        }
//
//        return dfs(0, 0)

        // DP space optimized solution
        var dp = mutableMapOf(0 to 1)
        for (num in nums) {
            val nextDp = mutableMapOf<Int, Int>()
            for ((sum, count) in dp) {
                nextDp[sum + num] = nextDp.getOrDefault(sum + num, 0) + count
                nextDp[sum - num] = nextDp.getOrDefault(sum - num, 0) + count
            }
            dp = nextDp
        }

        return dp[target] ?: 0
    }

    println(findTargetSumWays(intArrayOf(1, 1, 1, 1, 1), 3)) // 5
}
