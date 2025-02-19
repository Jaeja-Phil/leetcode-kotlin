package medium

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if
 * two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
 * can rob tonight without alerting the police.
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 1000
 *
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 */
fun main() {
    fun rob(nums: IntArray): Int {
        if (nums.size <= 3) {
            return nums.max()
        }

        fun robHousesInRange(start: Int, end: Int): Int {
            var prevMax = 0
            var currMax = 0
            for (i in start..end) {
                val temp = currMax
                currMax = maxOf(currMax, prevMax + nums[i])
                prevMax = temp
            }
            return currMax
        }

        return maxOf(robHousesInRange(0, nums.lastIndex - 1), robHousesInRange(1, nums.lastIndex))
    }

    println(rob(intArrayOf(2, 3, 2))) // 3
    println(rob(intArrayOf(1, 2, 3, 1))) // 4
}