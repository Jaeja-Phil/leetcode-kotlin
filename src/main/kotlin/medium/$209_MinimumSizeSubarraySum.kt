package medium

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Constraints:
 * - 1 <= target <= 10^9
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^5
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
fun main() {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var windowStart = 0
        var windowEnd = 0
        var localSum = 0
        var minLen = Int.MAX_VALUE
        while (windowEnd < nums.size) {
            val currentValue = nums[windowEnd]
            localSum += currentValue
            while (localSum >= target) {
                minLen = minOf(minLen, windowEnd - windowStart + 1)
                val startValue = nums[windowStart++]
                localSum -= startValue
            }
            windowEnd++
        }

        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }

    println(minSubArrayLen(7, intArrayOf(2, 3, 1, 2, 4, 3))) // 2
    println(minSubArrayLen(4, intArrayOf(1, 4, 4))) // 1
    println(minSubArrayLen(11, intArrayOf(1, 1, 1, 1, 1, 1, 1, 1))) // 0
}