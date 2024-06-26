package medium

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
 * nums[i], you can jump to any nums[i + j] where:
 * - 0 <= j <= nums[i] and
 * - i + j < n
 *
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1]
 *
 * constraints:
 * - 1 <= nums.length <= 10^4
 * - 0 <= nums[i] <= 1000
 * - it's guaranteed that you can reach the last index
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps
 * to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 */
fun main() {
    fun jump(nums: IntArray): Int {
        var jumpCount = 0
        var currentEnd = 0
        var currentFarthest = 0

        for (i in 0 ..< nums.lastIndex) {
            currentFarthest = maxOf(currentFarthest, i + nums[i])
            if (i == currentEnd) {
                jumpCount++
                currentEnd = currentFarthest
            }
        }

        return jumpCount
    }

    println(jump(intArrayOf(2, 3, 1, 1, 4))) // 2
    println(jump(intArrayOf(2, 3, 0, 1, 4))) // 2
    println(jump(intArrayOf(1, 1, 1, 1))) // 3
    println(jump(intArrayOf(3, 4, 3, 2, 5, 4, 3))) // 3
}
