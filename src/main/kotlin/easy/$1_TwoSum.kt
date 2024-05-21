package easy

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to
 * target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Constraints:
 * - 2 <= nums.length <= 10^4
 * - -10^9 <= nums[i] <= 10^9
 * - -10^9 <= target <= 10^9
 * - Only one valid answer exists.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
fun main() {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }

        return intArrayOf(-1, -1)
    }

    val input = intArrayOf(2, 7, 11, 15)
    val target = 9
    println(twoSum(input, target).contentToString()) // [0, 1]

    val input2 = intArrayOf(3, 2, 4)
    val target2 = 6
    println(twoSum(input2, target2).contentToString()) // [1, 2]

    val input3 = intArrayOf(3, 3)
    val target3 = 6
    println(twoSum(input3, target3).contentToString()) // [0, 1]
}
