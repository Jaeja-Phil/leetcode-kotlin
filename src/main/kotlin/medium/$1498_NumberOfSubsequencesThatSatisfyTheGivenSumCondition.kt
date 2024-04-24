package medium

/**
 * You are given an array of integers nums and an integer target.
 *
 * Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is
 * less or equal to target. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^6
 * - 1 <= target <= 10^6
 *
 * Example 1:
 * Input: nums = [3,5,6,7], target = 9
 * Output: 4
 * Explanation: There are 4 subarrays such that their minimum and maximum are less than or equal to target:
 * [3], [5], [3,5], [5,6].
 *
 * Example 2:
 * Input: nums = [3,3,6,8], target = 10
 * Output: 6
 * Explanation: There are 6 subarrays such that their minimum and maximum are less than or equal to target:
 * [3], [3], [3,3], [3,6], [3,3,6], [3,3,6,8].
 *
 * Example 3:
 * Input: nums = [2,3,3,4,6,7], target = 12
 * Output: 61
 * Explanation: There are 63 non-empty subarrays to have their minimum and maximum less than or equal to target:
 *
 */
fun main() {
    fun numSubSeq(nums: IntArray, target: Int): Int {
        // sort the nums array
        nums.sort()

        // set up two pointers
        val n = nums.size
        val mod = 1e9 + 7
        val power = IntArray(n + 1) { 1 }
        for (i in 1..n) {
            power[i] = (power[i - 1] * 2 % mod).toInt()
        }

        var res = 0
        var left = 0
        var right = n - 1
        while (left <= right) {
            if (nums[left] + nums[right] > target) {
                right--
            } else {
                res = ((res + power[right - left]) % mod).toInt()
                left++
            }
        }

        return res
    }

    println(numSubSeq(intArrayOf(3, 5, 6, 7), 9)) // 4
    println(numSubSeq(intArrayOf(3, 3, 6, 8), 10)) // 6
    println(numSubSeq(intArrayOf(2, 3, 3, 4, 6, 7), 12)) // 61
}