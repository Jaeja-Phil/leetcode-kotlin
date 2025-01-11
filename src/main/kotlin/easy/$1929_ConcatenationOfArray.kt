package easy

/**
 * Given an integer array nums of length n, you want to create an array ans of length 2n where ans[i] == nums[i]
 * and ans[i + n] == nums[i] for 0 <= i < n (0-indexed).
 *
 * Specifically, ans is the concatenation of two nums arrays.
 *
 * Return the array ans.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 1000
 * - 1 <= nums[i] <= 1000
 *
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [1,2,1,1,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[0],nums[1],nums[2]]
 * - ans = [1,2,1,1,2,1]
 *
 * Example 2:
 * Input: nums = [1,3,2,1]
 * Output: [1,3,2,1,1,3,2,1]
 * Explanation: The array ans is formed as follows:
 * - ans = [nums[0],nums[1],nums[2],nums[3],nums[0],nums[1],nums[2],nums[3]
 * - ans = [1,3,2,1,1,3,2,1]
 */
fun main() {
    fun getConcatenation(nums: IntArray): IntArray {
        // Solution 1.
        // return nums.copyOf() + nums.copyOf()

        // Solution 2.
        // val n = nums.size
        // val ans = IntArray(n * 2)
        // nums.forEachIndexed { index, i ->
        //     ans[index] = i
        //     ans[index + n] = i
        // }
        //
        // return ans

        // Solution 3.
        return IntArray(nums.size * 2) { nums[it % nums.size] }
    }

    println(getConcatenation(intArrayOf(1, 2, 1)).contentToString()) // [1,2,1,1,2,1]
    println(getConcatenation(intArrayOf(1, 3, 2, 1)).contentToString()) // [1,3,2,1,1,3,2,1]
}