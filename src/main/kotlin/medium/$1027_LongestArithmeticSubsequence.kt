package medium

/**
 * Given an array of integers nums, return the length of the longest arithmetic subsequence in nums.
 *
 * Note that:
 * - A subsequence is an array that can be derived from another array by deleting some or no elements without changing
 *   the order of the remaining elements.
 * - A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 *
 * Constraints:
 * - 2 <= nums.length <= 1000
 * - -10^6 <= nums[i] <= 10^6
 *
 * Example 1:
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation: The whole array is an arithmetic sequence with steps of 3.
 *
 * Example 2:
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation: The longest arithmetic subsequence is [4,7,10].
 *
 * Example 3:
 * Input: nums = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [20,15,10,5].
 */
fun main() {
    fun longestArithmeticSeqLength(nums: IntArray): Int {
        var max = 0
        val dp = Array(nums.size) { mutableMapOf<Int, Int>() }

        for (i in nums.indices) {
            for (j in 0..<i) {
                val diff = nums[i] - nums[j]
                val count = dp[j].getOrDefault(diff, 1) + 1
                dp[i][diff] = count
                max = maxOf(max, count)
            }
        }

        return max
    }

    println(longestArithmeticSeqLength(intArrayOf(3, 6, 9, 12))) // 4
    println(longestArithmeticSeqLength(intArrayOf(9, 4, 7, 2, 10))) // 3
    println(longestArithmeticSeqLength(intArrayOf(20, 1, 15, 3, 10, 5, 8))) // 4
}
