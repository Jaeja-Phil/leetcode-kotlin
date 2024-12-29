package medium

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 * Notice that the sequence has to be strictly increasing.
 *
 * Constraints:
 * - 1 <= nums.length <= 2000
 * - -10^6 <= nums[i] <= 10^6
 * - the answer is guaranteed to fit inside a 32-bit integer
 *
 * Example 1:
 * Input: nums = [1, 3, 5, 4, 7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: nums = [2, 2, 2, 2, 2]
 * Output: 5
 * Explanation: The length of the longest continuous increasing subsequence is 1, and there are 5 subsequences' length
 * is 1, so the answer is 5.
 */
fun main() {
    fun findNumberOfLIS(nums: IntArray): Int {
        val n = nums.size
        val dp = IntArray(n) { 1 }
        val count = IntArray(n) { 1 }
        var maxLen = 1
        for (i in 1..<n) { // right pointer
            for (j in 0..<i) { // left pointer, approaching right pointer
                if (nums[i] > nums[j]) { // if increasing
                    if (dp[j] + 1 > dp[i]) { // check whether new length is greater than current length
                        // if yes, update length and count
                        dp[i] = dp[j] + 1
                        count[i] = count[j]
                    } else if (dp[j] + 1 == dp[i]) { // check whether new length is equal to current length
                        // if yes, update count
                        // add count of left pointer to count of right pointer
                        // why? because we are counting "number" of LIS
                        count[i] += count[j]
                    }
                }
            }
            maxLen = maxOf(maxLen, dp[i])
        }

        var result = 0
        for (i in nums.indices) {
            if (dp[i] == maxLen) {
                result += count[i]
            }
        }

        return result
    }

    println(findNumberOfLIS(intArrayOf(1, 3, 5, 4, 7))) // 2
    println(findNumberOfLIS(intArrayOf(2, 2, 2, 2, 2))) // 5
}
