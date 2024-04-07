package medium

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a
 * given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - nums is a non-decreasing array.
 * - -10^9 <= target <= 10^9
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
fun main() {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        // base case
        if (nums.isEmpty() || nums.size == 1 && nums.first() != target) {
            return intArrayOf(-1, -1)
        }

        fun findTargetIndex(nums: IntArray, target: Int, isFirst: Boolean): Int {
            var left = 0
            var right = nums.lastIndex
            while (left <= right) {
                val mid = left + (right - left) / 2
                if (nums[mid] == target) {
                    if (isFirst) {
                        if (mid == 0 || nums[mid - 1] != target) {
                            return mid
                        }
                        right = mid - 1
                    } else {
                        if (mid == nums.lastIndex || nums[mid + 1] != target) {
                            return mid
                        }
                        left = mid + 1
                    }
                } else if (nums[mid] < target) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            return -1
        }

        val leftIdx = findTargetIndex(nums, target, true)
        if (leftIdx == -1) {
            return intArrayOf(-1, -1)
        }
        val rightIdx = findTargetIndex(nums, target, false)

        return intArrayOf(leftIdx, rightIdx)
    }

    println(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).contentToString()) // [3, 4]
    println(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6).contentToString()) // [-1, -1]
    println(searchRange(intArrayOf(), 0).contentToString()) // [-1, -1]
    println(searchRange(intArrayOf(2, 2), 3).contentToString()) // [0, 0]
}
