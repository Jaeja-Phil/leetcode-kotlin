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
        /**
         * For any number "i", what's the best product we can get by breaking it into at least two positive integers?
         * We can use dynamic programming to solve this problem.
         * > dp[i] = max product we can get from integer i
         *
         * since the constraint is 2 <= n, we can start from 2
         * for i == 2, we can only break it into 1 + 1, so dp[2] = 1 (which is initialized to 1)
         *
         * so, for i from 3 to n, we can break it into j + (i - j) for all j from 2 to i - 1
         * We have two options:
         * 1. don't break i - j any further, just multiply: j * (i - j)
         * 2. do break i - j further, so multiply j * dp[i - j]
         *
         * So, we can take the maximum of these options:
         * > dp[i] = maxOf(dp[i], j * (i - j), j * dp[i - j])
         *
         * try every valid j, and pick the best one
         */
        val dp = IntArray(n + 1)
        dp[1] = 1 // base case, we can't break 1 into two positive integers
        dp[2] = 1 // base case, we can only break 2 into 1 + 1, so the product is 1

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
