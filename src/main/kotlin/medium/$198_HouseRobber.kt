package medium

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and
 * it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 * constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 400
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */
fun main() {
    fun rob(nums: IntArray): Int {
        // base case
        if (nums.isEmpty()) return 0
        if (nums.size <= 2) return nums.max()

        /**
         * space non-optimized
         */
//        val dp = IntArray(nums.size)
//        dp[0] = nums[0]
//        dp[1] = maxOf(nums[0], nums[1])
//        for (i in 2..<nums.size) {
//            dp[i] = maxOf(dp[i - 1], dp[i - 2] + nums[i])
//        }
//
//        return dp[dp.lastIndex]

        /**
         * space optimized
         */
        var prev1 = nums[0]
        var prev2 = maxOf(nums[0], nums[1])
        for (i in 2..<nums.size) {
            val temp = prev2
            prev2 = maxOf(prev2, prev1 + nums[i])
            prev1 = temp
        }
        return prev2
    }

    println(rob(intArrayOf(1, 2, 3, 1))) // 4
    println(rob(intArrayOf(2, 7, 9, 3, 1))) // 12
}