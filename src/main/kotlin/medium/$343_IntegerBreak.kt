package medium

/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those
 * integers.
 *
 * Return the maximum product you can get.
 *
 * Constraints:
 * - 2 <= n <= 58
 *
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *
 * Example 2:
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 */
fun main() {
    fun integerBreak(n: Int): Int {
        val dp = IntArray(n + 1) { 1 }

        for (i in 3..n) {
            for (j in 2..<i) {
                dp[i] = maxOf(dp[i], j * dp[i - j], j * (i - j))
            }
        }

        return dp[n]
    }

    println(integerBreak(2)) // 1
    println(integerBreak(10)) // 36
}
