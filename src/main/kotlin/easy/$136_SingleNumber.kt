package easy

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 10^4
 * - -3 * 10^4 <= nums[i] <= 3 * 10^4
 * - Each element in the array appears twice except for one element which appears only once.
 *
 * Example 1:
 * Input: nums = [2,2,1]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 *
 * Example 3:
 * Input: nums = [1]
 * Output: 1
 */
fun main() {
    fun singleNumber(nums: IntArray): Int {
        var result = 0
        for (num in nums) {
            /**
             * XOR operation:
             * 0 xor 0 = 0
             * 0 xor 1 = 1
             * 1 xor 0 = 1
             * 1 xor 1 = 0
             *
             * do XOR operation because
             * - a number xor with itself cancels out (becomes 0)
             * - all duplicates will cancel out
             * - the only number that doesn't cancel out will be left as the result
             */
            result = result xor num
        }
        return result
    }

    println(singleNumber(intArrayOf(2, 2, 1))) // 1
    println(singleNumber(intArrayOf(4, 1, 2, 1, 2))) // 4
    println(singleNumber(intArrayOf(1))) // 1
}