package medium

/**
 * Given an integer array nums, find a subarray that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -10 <= nums[i] <= 10
 * - The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [2, 3, -2, 4]
 * Output: 6
 * Explanation: [2, 3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2, 0, -1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2, -1] is not a subarray.
 */
fun main() {
    fun maxProduct(nums: IntArray): Int {
        var maxProduct = nums[0]
        var minProduct = nums[0]
        var result = nums[0]

        for (i in 1 until nums.size) {
            val tempMaxProduct = maxOf(nums[i], maxProduct * nums[i], minProduct * nums[i])
            minProduct = minOf(nums[i], maxProduct * nums[i], minProduct * nums[i])
            maxProduct = tempMaxProduct
            result = maxOf(result, maxProduct)
        }

        return result
    }

    println(maxProduct(intArrayOf(2, 3, -2, 4))) // 6
    println(maxProduct(intArrayOf(-2, 0, -1))) // 0
}