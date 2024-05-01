package medium

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 0 <= k <= 10^5
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */
fun main() {
    fun rotate(nums: IntArray, k: Int) {
        // base case
        if (nums.isEmpty() || k == 0 || nums.size == 1 || k % nums.size == 0) return

        // 1. reverse the array
        nums.reverse()

        // 2. at pivot point of k, reverse left and right sub-arrays
        val pivot = k % nums.size
        nums.reverse(0, pivot)
        nums.reverse(pivot, nums.size)
    }

    val nums1 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val k1 = 3
    rotate(nums1, k1)
    println(nums1.contentToString()) // [5, 6, 7, 1, 2, 3, 4]

    val nums2 = intArrayOf(-1, -100, 3, 99)
    val k2 = 2
    rotate(nums2, k2)
    println(nums2.contentToString()) // [3, 99, -1, -100]
}
