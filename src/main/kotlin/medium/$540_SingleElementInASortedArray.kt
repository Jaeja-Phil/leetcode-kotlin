package medium

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one
 * element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 * constraints:
 * - 1 <= nums.length <= 10^5
 * - 0 <= nums[i] <= 10^5
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */
fun main() {
    fun singleNonDuplicate(nums: IntArray): Int {
        if (nums.size == 1) return nums.first()

        var left = 0
        var right = nums.lastIndex

        while (left < right) {
            val mid = left + (right - left) / 2
            val areHalvesEven = (right - mid) % 2 == 0

            if (nums[mid] == nums[mid - 1]) {
                if (areHalvesEven) {
                    right = mid - 2
                } else {
                    left = mid + 1
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (areHalvesEven) {
                    left = mid + 2
                } else {
                    right = mid - 1
                }
            } else {
                return nums[mid]
            }
        }

        return nums[left]
    }

    println(singleNonDuplicate(intArrayOf(1,1,2,3,3,4,4,8,8))) // 2
    println(singleNonDuplicate(intArrayOf(3,3,7,7,10,11,11))) // 10
}
