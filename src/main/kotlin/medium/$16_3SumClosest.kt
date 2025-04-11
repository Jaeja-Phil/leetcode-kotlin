package medium

import kotlin.math.abs

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is
 * closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 * Constraints:
 * - 3 <= nums.length <= 1000
 * - -1000 <= nums[i] <= 1000
 * - -10^4 <= target <= 10^4
 *
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Example 2:
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 */
fun main() {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        val n = nums.size
        nums.sort()
        var closest = nums[0] + nums[1] + nums[2]
        for (i in 0..<n - 2) {
            var left = i + 1
            var right = nums.lastIndex
            while (left < right) {
                val cur = nums[i] + nums[left] + nums[right]
                if (abs(cur - target) < abs(closest - target)) {
                    closest = cur
                }
                when {
                    cur > target -> right--
                    cur < target -> left++
                    else -> return closest
                }
            }
        }

        return closest
    }

    // Example usage
    val nums = intArrayOf(-1, 2, 1, -4)
    val target = 1
    val result = threeSumClosest(nums, target)
    println("The sum that is closest to the target is: $result") // Output: 2

    // Example usage
    val nums2 = intArrayOf(0, 0, 0)
    val target2 = 1
    val result2 = threeSumClosest(nums2, target2)
    println("The sum that is closest to the target is: $result2") // Output: 0
}