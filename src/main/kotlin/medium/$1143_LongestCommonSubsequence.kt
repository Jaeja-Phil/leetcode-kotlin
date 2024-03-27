package medium

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no
 * common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters
 * (can be none) deleted without changing the relative order of the remaining characters.
 * - For example, "ace" is a subsequence of "abcde".
 *
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * constraints:
 * - 1 <= text1.length, text2.length <= 1000
 * - text1 and text2 consist of only lowercase English characters.
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace".
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc".
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 */
fun main() {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val t1Length = text1.length
        val t2Length = text2.length
        // take care of the edge cases, be mindful of the constraints
        if (t1Length == 1 && t2Length == 1) {
            return if (text1 == text2) 1 else 0
        }

        // create a 2D array to store the length of the longest common subsequence
        // + 1 to account for the empty string
        val dp = Array(t1Length + 1) { IntArray(t2Length + 1) }
        (1..t1Length).forEach { dpRowIdx ->
            (1..t2Length).forEach { dpColIdx ->
                val text1Letter = text1[dpRowIdx - 1]
                val text2Letter = text2[dpColIdx - 1]
                dp[dpRowIdx][dpColIdx] = when {
                    text1Letter == text2Letter -> dp[dpRowIdx - 1][dpColIdx - 1] + 1
                    else -> maxOf(dp[dpRowIdx - 1][dpColIdx], dp[dpRowIdx][dpColIdx - 1])
                }
            }
        }

        return dp[t1Length][t2Length]
    }

    println(longestCommonSubsequence("abcde", "ace")) // 3
    println(longestCommonSubsequence("abc", "abc")) // 3
    println(longestCommonSubsequence("abc", "def")) // 0
}