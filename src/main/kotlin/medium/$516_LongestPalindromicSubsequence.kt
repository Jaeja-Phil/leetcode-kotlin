package medium

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
 * changing the order of the remaining elements.
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consists only of lowercase English letters.
 *
 * Example 1:
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 * Ref: https://youtu.be/_nCsPn7_OgI
 */
fun main() {
    fun longestPalindromicSubsequence(s: String): Int {
        val n = s.length
        // create a 2D array to store the length of the longest palindromic subsequence
        val dp = Array(n) { IntArray(n) }

        // initialize the diagonal elements to 1, since a single character is a palindrome
        for (i in s.indices) {
            dp[i][i] = 1
        }

        // iterate through the string and fill the dp array
        for (i in n - 2 downTo 0) {  // `i` is now the outer loop (left boundary)
            for (j in i + 1..<n) {  // `j` is the inner loop (right boundary)
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2
                } else {
                    dp[i][j] = maxOf(dp[i + 1][j], dp[i][j - 1])
                }
            }
        }

        // the longest palindromic subsequence will be stored in the top right corner of the dp array
        return dp[0][n - 1]
    }

    println(longestPalindromicSubsequence("bbbab")) // 4
    println(longestPalindromicSubsequence("cbbd")) // 2
}