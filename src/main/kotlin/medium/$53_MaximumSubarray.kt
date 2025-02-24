package medium

/**
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: [1] has the largest sum = 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: [5,4,-1,7,8] has the largest sum = 23.
 */
fun main() {
    fun maxSubArray(nums: IntArray): Int {
        var maxSub = nums[0]
        var curSum = 0
        for (num in nums) {
            if (curSum < 0) {
                curSum = 0
            }
            curSum += num
            maxSub = maxOf(maxSub, curSum)
        }
        return maxSub
    }

    println(maxSubArray(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4))) // 6
    println(maxSubArray(intArrayOf(1))) // 1
    println(maxSubArray(intArrayOf(5, 4, -1, 7, 8))) // 23
    println(maxSubArray(intArrayOf(-2, 1))) // 1
}
