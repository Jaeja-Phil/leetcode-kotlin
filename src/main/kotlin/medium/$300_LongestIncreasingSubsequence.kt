package medium

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence
 *
 * constraints:
 * - 1 <= nums.length <= 2500
 * - -10^4 <= nums[i] <= 10^4
 *
 * Example 1:
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Example 2:
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 */
fun main() {
    /**
     * solution 1: dynamic programming
     */
    fun lengthOfLIS(nums: IntArray): Int {
        val dp = IntArray(nums.size) { 1 }
        for (i in nums.indices) {
            for (j in 0 ..< i) {
                if (nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
        }
        return dp.last()
    }

    /**
     * solution 2: intelligent subsequence building
     */
    fun lengthOfLIS2(nums: IntArray): Int {
        val subsequence = mutableListOf<Int>()
        subsequence.add(nums[0])

        for (i in 1 ..< nums.size) {
            if (nums[i] > subsequence.last()) {
                subsequence.add(nums[i])
            } else {
                val index = subsequence.binarySearch(nums[i])
                if (index < 0) {
                    subsequence[-index - 1] = nums[i]
                }
            }
        }
        return subsequence.size
    }

    println(lengthOfLIS(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18))) // 4
    println(lengthOfLIS(intArrayOf(0, 1, 0, 3, 2, 3))) // 4
    println(lengthOfLIS(intArrayOf(7, 7, 7, 7, 7, 7, 7))) // 1
}