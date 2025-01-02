package medium

/**
 * You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are
 * given) on two separate horizontal lines.
 *
 * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
 * - nums1[i] == nums2[j], and
 * - the line we draw does not intersect any other connecting (non-horizontal) line.
 *
 * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one
 * connecting line).
 *
 * Return the maximum number of connecting lines we can draw in this way.
 *
 * Constraints:
 * - 1 <= nums1.length, nums2.length <= 500
 * - 1 <= nums1[i], nums2[i] <= 2000
 *
 * Example 1:
 * Input: nums1 = [1,4,2], nums2 = [1,2,4]
 * Output: 2
 *
 * Example 2:
 * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 * Output: 3
 */
fun main() {
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        val n1 = nums1.size
        val n2 = nums2.size
        val dp = Array(n1 + 1) { IntArray(n2 + 1) }
        for (i in 1..n1) {
            for (j in 1..n2) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }

        return dp[n1][n2]
    }

    println(maxUncrossedLines(intArrayOf(1, 4, 2), intArrayOf(1, 2, 4))) // 2
    println(maxUncrossedLines(intArrayOf(2, 5, 1, 2, 5), intArrayOf(10, 5, 2, 1, 5, 2))) // 3
}
