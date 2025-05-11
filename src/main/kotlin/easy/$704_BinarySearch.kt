package easy

/**
 * Given an array of integers nums which is sorted in ascending order, and an integer target, write a function
 * to search target in nums. If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 < nums[i], target < 10^4
 * - all integers in nums are unique
 * - nums is sorted in ascending order
 *
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 *
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
fun main() {
    fun search(nums: IntArray, target: Int): Int {
        // Solution 1.
//        var left = 0
//        var right = nums.lastIndex
//
//        while (left < right) {
//            val mid = left + (right - left) / 2
//            val currentNum = nums[mid]
//            when {
//                currentNum == target -> return mid
//                currentNum > target -> right = mid - 1
//                else -> left = mid + 1
//            }
//        }
//
//        return if (nums[left] == target) left else -1

        // Solution 2.
//        var left = 0
//        var right = nums.lastIndex
//
//        while (left <= right) {
//            val mid = left + (right - left) / 2
//            val currentNum = nums[mid]
//            when {
//                currentNum > target -> right = mid - 1
//                currentNum < target -> left = mid + 1
//                else -> return mid
//            }
//        }
//
//        return -1

        // Solution 3.
        fun binarySearch(l: Int, r: Int, nums: IntArray, target: Int): Int {
            if (l > r) return -1
            val mid = l + (r - l) / 2
            return when {
                nums[mid] == target -> mid
                nums[mid] < target -> binarySearch(mid + 1, r, nums, target)
                else -> binarySearch(l, mid - 1, nums, target)
            }
        }

        return binarySearch(0, nums.lastIndex, nums, target)
    }

    println(search(intArrayOf(-1, 0, 3, 5, 9, 12), 9)) // 4
    println(search(intArrayOf(-1, 0, 3, 5, 9, 12), 2)) // -1
}