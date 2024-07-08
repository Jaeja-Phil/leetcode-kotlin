package medium

/**
 * Given two strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * - s = s1 + s2 + ... + sn
 * - t = t1 + t2 + ... + tm
 * - |n - m| <= 1
 * - The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 *
 * Constraints:
 * - 0 <= s1.length, s2.length <= 100
 * - 0 <= s3.length <= 200
 * - s1, s2, and s3 consist of lowercase English letters.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
fun main() {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        // base case
        if (s1.length + s2.length != s3.length) return false

        val dp = Array(s1.length + 1) { BooleanArray(s2.length + 1) }

        for (i in 0..s1.length) {
            for (j in 0..s2.length) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2[j - 1] == s3[j - 1]
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1[i - 1] == s3[i - 1]
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1]) ||
                            (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1])
                }
            }
        }

        return dp[s1.length][s2.length]
    }

    val s1 = "aabcc"
    val s2 = "dbbca"
    val s3 = "aadbbcbcac"
    println(isInterleave(s1, s2, s3)) // true

    val s4 = "aabcc"
    val s5 = "dbbca"
    val s6 = "aadbbbaccc"
    println(isInterleave(s4, s5, s6)) // false

}