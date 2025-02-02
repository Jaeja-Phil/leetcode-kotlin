package easy

/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return
 * the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums contains distinct values sorted in ascending order.
 * - -10^4 <= target <= 10^4
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 */
fun main() {
    fun searchInsert(nums: IntArray, target: Int): Int {
        // Solution 1.
//        var left = 0
//        var right = nums.lastIndex
//        while (left < right) {
//            val mid = left + (right - left) /2
//            when {
//                nums[mid] == target -> return mid
//                nums[mid] > target -> right = mid -1
//                else -> left = mid + 1
//            }
//        }
//
//        return if (nums[left] >= target) left else left + 1

        // Solution 2.
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                target > nums[mid] -> left = mid + 1
                target < nums[mid] -> right = mid - 1
                else -> return mid
            }
        }

        return left
    }

    println(searchInsert(intArrayOf(1, 3, 5, 6), 5)) // 2
    println(searchInsert(intArrayOf(1, 3, 5, 6), 2)) // 1
    println(searchInsert(intArrayOf(1, 3, 5, 6), 7)) // 4
    println(searchInsert(intArrayOf(1, 3, 5, 6), 0)) // 0
}