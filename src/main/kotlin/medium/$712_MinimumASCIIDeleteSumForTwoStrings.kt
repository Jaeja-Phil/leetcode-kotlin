package medium

/**
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 *
 * Constraints:
 * - 1 <= s1.length, s2.length <= 1000
 * - s1 and s2 consist of lowercase English letters.
 *
 * Example 1:
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 *
 * Example 2:
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d] + 101[e] + 101[e] to the sum. Deleting "t" from "leet" adds 116[t] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100 + 101 + 101 + 116 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 */
fun main() {
    fun minimumDeleteSum(s1: String, s2: String): Int {
        // initialize 2D dp array
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }

        // fill the first row
        for (i in 1..s1.length) {
            dp[i][0] = dp[i - 1][0] + s1[i - 1].code
        }

        // fill the first column
        for (j in 1..s2.length) {
            dp[0][j] = dp[0][j - 1] + s2[j - 1].code
        }

        // fill the rest of the dp array
        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                dp[i][j] = if (s1[i - 1] == s2[j - 1]) {
                    dp[i - 1][j - 1]
                } else {
                    minOf(dp[i - 1][j] + s1[i - 1].code, dp[i][j - 1] + s2[j - 1].code)
                }
            }
        }

        return dp[s1.length][s2.length]
    }

    println(minimumDeleteSum("sea", "eat")) // 231
    println(minimumDeleteSum("delete", "leet")) // 403
}
