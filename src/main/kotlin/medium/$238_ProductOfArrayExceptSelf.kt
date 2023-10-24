package medium

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the
 * product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 */
fun main() {
    val res1 = `Product of Array Except Self`(intArrayOf(1, 2, 3, 4))
    println(res1.joinToString()) // 24,12,8,6

    val res2 = `Product of Array Except Self`(intArrayOf(-1, 1, 0, -3, 3))
    println(res2.joinToString()) // 0,0,9,0,0
}

fun `Product of Array Except Self`(nums: IntArray): IntArray {
    /**
     * create 2 arrays to store the products accumulating from left and right
     */
    val productsAccumulatingFromLeft = IntArray(nums.size)
    val productsAccumulatingFromRight = IntArray(nums.size)

    /**
     * accumulating from left should have 1 as the first element
     * accumulating from right should have 1 as the last element
     */
    productsAccumulatingFromLeft[0] = 1
    productsAccumulatingFromRight[nums.lastIndex] = 1

    /**
     * accumulating from left
     */
    for (i in 1..nums.lastIndex) {
        productsAccumulatingFromLeft[i] = productsAccumulatingFromLeft[i - 1] * nums[i - 1]
    }

    /**
     * accumulating from right
     */
    for (i in nums.lastIndex - 1 downTo 0) {
        productsAccumulatingFromRight[i] = productsAccumulatingFromRight[i + 1] * nums[i + 1]
    }

    val result = IntArray(nums.size)
    /**
     * multiply the products accumulating from left and right
     */
    for (i in nums.indices) {
        result[i] = productsAccumulatingFromLeft[i] * productsAccumulatingFromRight[i]
    }

    /**
     * return the result
     */
    return result
}