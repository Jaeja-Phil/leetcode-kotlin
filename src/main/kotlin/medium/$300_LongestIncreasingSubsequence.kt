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
     *
     * Intuition:
     * At each index "i", we want to know:
     * > "what is the LIS that ends at index i?"
     *
     * To find this, we look at all previous indices j < i.
     * If nums[j] < nums[i], it means nums[i] can extend the LIS that ends at index j.
     *
     * So we can say:
     * > dp[i] = maxOf(dp[i], dp[j] + 1)
     *
     * We also initialize dp[i] to 1, because the LIS that ends at index i is at least 1 (the number itself).
     *
     * Ex)
     * nums = [10, 9, 2, 5, 3, 7, 101, 18]
     * initialize dp = [1, 1, 1, 1, 1, 1, 1, 1]
     *
     * i = 0, j = 0 --> do nothing (no j < i)
     * i = 1, j = 0 --> nums[1] < nums[0] --> do nothing
     * i = 2, j = 0 --> nums[2] < nums[0] --> do nothing
     * i = 2, j = 1 --> nums[2] < nums[1] --> do nothing
     * i = 3, j = 0 --> nums[3] < nums[0] --> do nothing
     * i = 3, j = 1 --> nums[3] < nums[1] --> do nothing
     * i = 3, j = 2 --> nums[3] > nums[2] --> dp[3] = maxOf(dp[3], dp[2] + 1) = maxOf(1, 1 + 1) = 2
     * i = 4, j = 0 --> nums[4] < nums[0] --> do nothing
     * ...
     *
     * resulting dp = [1, 1, 1, 2, 2, 3, 4, 4]
     *
     * Finally, we return the maximum value in dp, which is the length of the longest increasing subsequence.
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
        return dp.max()
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