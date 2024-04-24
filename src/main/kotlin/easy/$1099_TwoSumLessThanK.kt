package easy

/**
 * Given an array nums of integers and integer "k", return the maximum sum such that there exists i < j with
 * nums[i] + nums[j] = sum and sum < k. If no i, j exist satisfying this equation, return -1.
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 1 <= nums[i] <= 1000
 * - 1 <= k <= 2000
 *
 * Example 1:
 * Input: nums = [34,23,1,24,75,33,54,8], k = 60
 * Output: 58
 * Explanation: We can use 34 and 24 to sum 58 which is less than 60.
 *
 * Example 2:
 * Input: nums = [10,20,30], k = 15
 * Output: -1
 * Explanation: In this case it is not possible to get a pair sum less that 15.
 */
fun main() {
    fun twoSumLessThanK(nums: IntArray, k: Int): Int {
        // sort given array
        nums.sort()

        var left = 0
        var right = nums.lastIndex
        var max = -1
        while (left < right) {
            val elementI = nums[left]
            val elementJ = nums[right]
            val sum = elementI + elementJ
            when {
                sum < k -> {
                    max = maxOf(max, sum)
                    left++
                }
                else -> right--
            }
        }

        return max
    }

//    println(twoSumLessThanK(intArrayOf(34,23,1,24,75,33,54,8), 60)) // 58
//    println(twoSumLessThanK(intArrayOf(10,20,30), 15)) // -1
    println(twoSumLessThanK(intArrayOf(254,914,110,900,147,441,209,122,571,942,136,350,160,127,178,839,201,386,462,45,735,467,153,415,875,282,204,534,639,994,284,320,865,468,1,838,275,370,295,574,309,268,415,385,786,62,359,78,854,944), 200)) // -1
}