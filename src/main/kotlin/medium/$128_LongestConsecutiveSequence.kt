package medium

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 */
fun main() {
    fun longestConsecutive(nums: IntArray): Int {
        val set = nums.toHashSet()
        var longestStreak = 0

        for (num in set) {
            if (set.contains(num - 1).not()) {
                var currentNum = num
                var currentStreak = 1

                while (set.contains(currentNum + 1)) {
                    currentNum++
                    currentStreak++
                }

                longestStreak = maxOf(longestStreak, currentStreak)
            }
        }

        return longestStreak
    }

    val nums1 = intArrayOf(100, 4, 200, 1, 3, 2)
    println(longestConsecutive(nums1)) // 4

    val nums2 = intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)
    println(longestConsecutive(nums2)) // 9
}
