package medium

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Constraints:
 * - 1 <= nums.length <= 5000
 * - -10^4 <= nums[i] <= 10^4
 * - All values of nums are unique.
 * - nums is guaranteed to be rotated at some pivot.
 * - -10^4 <= target <= 10^4
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 */
fun main() {
    fun search(nums: IntArray, target: Int): Int {
        fun findPivotIdx(nums: IntArray): Int {
            var left = 0
            var right = nums.lastIndex

            while (left <= right) {
                val mid = left + (right - left) / 2
                if (nums[mid] > nums.last()) {
                    /**
                     * since the array is sorted in ascending order, if the mid element is greater than the last element,
                     * then the pivot is on the right side of the mid element
                     */
                    left = mid + 1
                } else {
                    /**
                     * if the mid element is less than the last element, then the pivot is on the left side of the mid element
                     */
                    right = mid - 1
                }
            }

            return left
        }

        // 1. find pivot
        val pivotIdx = findPivotIdx(nums)

        // 2. find target
        var left = 0
        var right = nums.lastIndex
        val numsSize = nums.size
        while (left <= right) {
            val mid = left + (right - left) / 2
            val midIdx = (mid + pivotIdx) % numsSize
            when {
                nums[midIdx] == target -> return midIdx
                nums[midIdx] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }

        return -1
    }

    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0)) // 4
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3)) // -1
    println(search(intArrayOf(1), 0)) // -1
}