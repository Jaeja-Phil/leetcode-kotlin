package hard

/**
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 *
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 */
fun main() {
    // time complexity: O(n)
    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size

        for (i in nums.indices) {
            while (
                // check if the current number is in the range of 1 to n
                nums[i] in 1..n &&
                // check if the current number is not in the correct position
                nums[i] != i + 1 &&
                // check if the current number is not equal to the number at the correct position
                nums[nums[i] - 1] != nums[i]
            ) {
                // swap the current number with the number at the correct position
                nums[i] = nums[nums[i] - 1].also { nums[nums[i] - 1] = nums[i] }
            }
        }

        nums.forEachIndexed { index, currNum ->
            if (currNum != index + 1) {
                return index + 1
            }
        }

        return n + 1
    }

    println(firstMissingPositive(intArrayOf(1, 2, 0))) // 3
    println(firstMissingPositive(intArrayOf(2, 1))) // 3
    println(firstMissingPositive(intArrayOf(3, 4, -1, 1))) // 2
    println(firstMissingPositive(intArrayOf(7, 8, 9, 11, 12))) // 1
}