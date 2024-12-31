package medium

/**
 * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is
 * an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 *
 * A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order
 * of the remaining elements.
 *
 * Constraints:
 * - 1 <= arr.length <= 10^5
 * - -10^4 <= arr[i], difference <= 10^4
 *
 * Example 1:
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 *
 * Example 2:
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 *
 * Example 3:
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 */
fun main() {
    fun longestSubsequence(arr: IntArray, targetDiff: Int): Int {
        val map = mutableMapOf<Int, Int>()
        var max = 0

        for (i in arr.indices) {
            val diff = arr[i] - targetDiff
            val count = map.getOrDefault(diff, 0) + 1
            map[arr[i]] = count
            max = maxOf(max, count)
        }

        return max
    }

    println(longestSubsequence(intArrayOf(1, 2, 3, 4), 1)) // 4
    println(longestSubsequence(intArrayOf(1, 3, 5, 7), 1)) // 1
    println(longestSubsequence(intArrayOf(1, 5, 7, 8, 5, 3, 4, 2, 1), -2)) // 4
}