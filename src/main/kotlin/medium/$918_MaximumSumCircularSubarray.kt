package medium

/**
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of
 * nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i],
 * nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 3 * 10^4
 * - -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * Example 1:
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 *
 * Example 2:
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
 *
 * Example 3:
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2
 */
fun main() {
    fun maxSubarraySumCircular(nums: IntArray): Int {
        var maxSum = nums[0]
        var curMax = 0
        var minSum = nums[0]
        var curMin = 0
        var totalSum = 0

        for (num in nums) {
            // current max is either the current number itself or the sum of current number and previous max
            curMax = maxOf(curMax + num, num)
            // max sum is the maximum of current max and previous max sum
            maxSum = maxOf(maxSum, curMax)

            // current min is either the current number itself or the sum of current number and previous min
            curMin = minOf(curMin + num, num)
            // min sum is the minimum of current min and previous min sum
            minSum = minOf(minSum, curMin)

            // get the total sum while iterating through the array
            totalSum += num
        }

        // if maxSum is less than 0, there must have been only negative numbers in the array
        // in this case, return the maxSum itself
        // if this is not the case, we need to compare maxSum with the totalSum - minSum
        // totalSum - minSum means we're excluding the minimum subarray from total which will give us the maximum subarray
        // this may happen when the maximum subarray is circular
        return if (maxSum < 0) maxSum else maxOf(maxSum, totalSum - minSum)
    }

    println(maxSubarraySumCircular(intArrayOf(1, -2, 3, -2))) // 3
    println(maxSubarraySumCircular(intArrayOf(5, -3, 5))) // 10
}