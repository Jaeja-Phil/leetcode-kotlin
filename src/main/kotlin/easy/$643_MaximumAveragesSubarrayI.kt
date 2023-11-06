package easy

/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value
 * and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 * constraints:
 * - n == nums.length
 * - 1 <= k <= n <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * Example 1:
 * Input: nums = [1, 12, -5, -6, 50, 3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 */
fun main() {
    val res1 = `Maximum Average Subarray I`(intArrayOf(1, 12, -5, -6, 50, 3), 4)
    println(res1) // 12.75000

    val res2 = `Maximum Average Subarray I`(intArrayOf(5), 1)
    println(res2) // 5.00000
}

fun `Maximum Average Subarray I`(nums: IntArray, k: Int): Double {
        /**
         * create two pointers for sliding window
         */
        var windowLeft = 0
        var windowRight = 0

        /**
         * create a variable to hold the max sum and current sum
         */
        var maxSum = Int.MIN_VALUE
        var currentSum = 0

        /**
         * loop through the array
         */
        while (windowRight < nums.size) {
            /**
             * store & add number on the right side of the window
             */
            val rightNum = nums[windowRight]
            currentSum += rightNum

            /**
             * if the window is full, check if the current sum is greater than the max sum
             */
            val windowLength = windowRight - windowLeft + 1
            if (windowLength == k) {
                maxSum = maxOf(maxSum, currentSum)
                /**
                 * subtract the number on the left side of the window and slide it by one
                 */
                val leftNum = nums[windowLeft]
                currentSum -= leftNum
                windowLeft++
            }

            windowRight++
        }


        return maxSum / k.toDouble()
}