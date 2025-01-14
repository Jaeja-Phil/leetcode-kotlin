package medium

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color
 * are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 300
 * - nums[i] is 0, 1, or 2.
 *
 * Example 1:
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Example 2:
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 */
fun main() {
    fun sortColors(nums: IntArray) {
        var left = 0
        var right = nums.lastIndex
        var i = 0

        fun swap(i: Int, j: Int) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }

        while (i <= right) {
            when {
                nums[i] == 0 -> {
                    swap(i, left)
                    left++
                    i++
                }
                nums[i] == 2 -> {
                    swap(i, right)
                    right--
                }
                else -> i++
            }
        }
    }

    val nums1 = intArrayOf(2, 0, 2, 1, 1, 0)
    sortColors(nums1)
    println(nums1.contentToString()) // [0,0,1,1,2,2]

    val nums2 = intArrayOf(2, 0, 1)
    sortColors(nums2)
    println(nums2.contentToString()) // [0,1,2]
}