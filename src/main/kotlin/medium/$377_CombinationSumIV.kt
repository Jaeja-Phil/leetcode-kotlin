package medium

/**
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that
 * add up to target.
 *
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 *
 * Constraints:
 * - 1 <= nums.length <= 200
 * - 1 <= nums[i] <= 1000
 * - All elements of nums are unique.
 * - 1 <= target <= 1000
 *
 * Example 1:
 * Input: nums = [1, 2, 3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1) 1, 1, 1, 1
 * (2) 1, 1, 2
 * (3) 1, 2, 1
 * (4) 1, 3
 * (5) 2, 1, 1
 * (6) 2, 2
 * (7) 3, 1
 *
 * Example 2:
 * Input: nums = [9], target = 3
 * Output: 0
 */
fun main() {
    fun combinationSum(nums: IntArray, target: Int): Int {
        val dp = IntArray(target + 1)
        dp[0] = 1

        for (i in 1..target) {
            for (num in nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num]
                }
            }
        }

        return dp[target]
    }

    println(combinationSum(intArrayOf(1, 2, 3), 4)) // 7
    println(combinationSum(intArrayOf(9), 3)) // 0
}