package medium

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and using only constant extra space.
 *
 * Constraints:
 * - 1 <= n <= 10^5
 * - nums.length == n + 1
 * - 1 <= nums[i] <= n
 * - All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * Example 3:
 * Input: nums = [3,3,3,3,3]
 * Output: 3
 */
fun main() {
    fun findDuplicate(nums: IntArray): Int {
        // Solution 1.
        val seen = mutableSetOf<Int>()
        for (num in nums) {
            if (!seen.add(num)) {
                return num
            }
        }

        return -1

        // Solution 2.
        // Floyd's Tortoise and Hare (Cycle Detection) Algorithm
//        var slow = nums[0]
//        var fast = nums[nums[0]]
//
//        while (slow != fast) {
//            slow = nums[slow]
//            fast = nums[nums[fast]]
//        }
//
//        var slow2 = 0
//        while (slow != slow2) {
//            slow = nums[slow]
//            slow2 = nums[slow2]
//        }
//
//        return slow
    }

    println(findDuplicate(intArrayOf(1, 3, 4, 2, 2))) // 2
    println(findDuplicate(intArrayOf(3, 1, 3, 4, 2))) // 3
    println(findDuplicate(intArrayOf(3, 3, 3, 3, 3))) // 3
}