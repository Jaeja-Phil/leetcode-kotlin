package medium

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consist of only digits and English letters
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
fun main() {
    fun longestPalindrome(s: String): String {
        val n = s.length
        val dp = Array(n) { BooleanArray(n) }
        var start = 0
        var maxLength = 1

        for (i in 0 ..< n) {
            dp[i][i] = true
        }

        for (i in 0 ..< n - 1) {
            if (s[i] == s[i + 1]) {
                dp[i][i + 1] = true
                start = i
                maxLength = 2
            }
        }

        for (len in 3..n) {
            for (i in 0 ..< n - len + 1) {
                val j = i + len - 1
                if (s[i] == s[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true
                    start = i
                    maxLength = len
                }
            }
        }

        return s.substring(start, start + maxLength)
    }

    val s1 = "babad"
    println(longestPalindrome(s1)) // bab

    val s2 = "cbbd"
    println(longestPalindrome(s2)) // bb
}
