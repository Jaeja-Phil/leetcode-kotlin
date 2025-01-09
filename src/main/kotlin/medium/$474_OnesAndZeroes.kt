package medium

/**
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 * Constraints:
 * - 1 <= strs.length <= 600
 * - 1 <= strs[i].length <= 100
 * - strs[i] consists only of digits '0' and '1'.
 * - 1 <= m, n <= 100
 *
 * Example 1:
 * Input: strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * Output: 4
 *
 * Example 2:
 * Input: strs = ["10", "0", "1"], m = 1, n = 1
 * Output: 2
 */
fun main() {
    data class StringInfo(
        val zeros: Int,
        val ones: Int,
    )

    fun findMaxForm(strs: Array<String>, m: Int, n: Int): Int {
        val stringInfos = strs.map {
            val zeros = it.count { c -> c == '0' }
            val ones = it.count { c -> c == '1' }
            StringInfo(zeros, ones)
        }

        val dp = Array(m + 1) { IntArray(n + 1) }

        for (stringInfo in stringInfos) {
            for (i in m downTo stringInfo.zeros) {
                for (j in n downTo stringInfo.ones) {
                    // dp[i][j] = max number of strings that can be formed with i zeros and j ones
                    dp[i][j] = maxOf(
                        // Don't include the current string
                        dp[i][j],
                        // Include the current string
                        dp[i - stringInfo.zeros][j - stringInfo.ones] + 1
                    )
                }
            }
        }

        return dp[m][n]
    }

    println(
        findMaxForm(
            arrayOf("10", "0001", "111001", "1", "0"),
            5,
            3
        )
    ) // 4

    println(
        findMaxForm(
            arrayOf("10", "0", "1"),
            1,
            1
        )
    ) // 2
}