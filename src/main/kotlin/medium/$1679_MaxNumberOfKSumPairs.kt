package medium

/**
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array
 *
 * constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^9
 * - 1 <= k <= 10^9
 *
 * Example 1:
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 *
 * Example 2:
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 */
fun main() {
    val res1 = `Max Number of K-Sum Pairs`(intArrayOf(1, 2, 3, 4), 5)
    println(res1) // 2

    val res2 = `Max Number of K-Sum Pairs`(intArrayOf(3, 1, 3, 4, 3), 6)
    println(res2) // 1
}

fun `Max Number of K-Sum Pairs`(nums: IntArray, k: Int): Int {
    /**
     * first, sort the nums array
     */
    nums.sort()

    /**
     * create two pointers, at each end of the array
     */
    var left = 0
    var right = nums.lastIndex

    /**
     * create a variable to hold the number of operations
     */
    var count = 0

    /**
     * while the pointers haven't crossed each other...
     */
    while (left < right) {
        /**
         * if the sum of the two pointers is equal to k, increment the count
         * slide the left and right pointers
         */
        if (nums[left] + nums[right] == k) {
            count++
            left++
            right--
        } else if (nums[left] + nums[right] < k) { // if the sum of the two pointers is less than k, slide the left pointer
            left++
        } else { // if the sum of the two pointers is greater than k, slide the right pointer
            right--
        }
    }

    /**
     * return the count
     */
    return count
}