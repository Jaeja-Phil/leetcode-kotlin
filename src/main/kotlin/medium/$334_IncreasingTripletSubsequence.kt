package medium

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k)
 * such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 * constraints:
 * - 1 <= nums.length <= 5 * 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 *
 * Example 2:
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 *
 * Example 3:
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 */
fun main() {
    val nums = intArrayOf(1,2,3,4,5)
    println(`Increasing Triplet Subsequence`(nums)) // true

    val nums2 = intArrayOf(5,4,3,2,1)
    println(`Increasing Triplet Subsequence`(nums2)) // false

    val nums3 = intArrayOf(2,1,5,0,4,6)
    println(`Increasing Triplet Subsequence`(nums3)) // true
}

fun `Increasing Triplet Subsequence`(nums: IntArray): Boolean {
    /**
     * cannot make a triplet if the array size is less than 3, return false
     */
    if (nums.size < 3) {
        return false
    }

    /**
     * set two variables to store the first and second smallest number
     */
    var first = Int.MAX_VALUE
    var second = Int.MAX_VALUE

    /**
     * if the current number is smaller than the first, update the first
     * if the current number is smaller than the second, update the second
     * if the current number is greater than the second, return true
     */
    for (i in nums.indices) {
        if (nums[i] <= first) {
            first = nums[i]
        } else if (nums[i] <= second) {
            second = nums[i]
        } else {
            return true
        }
    }

    /**
     * if the loop ends, it means there is no triplet, return false
     */
    return false
}