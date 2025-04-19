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
        // base case
        if (s.length <= 1) return s

        var maxLen = 1
        var start = 0
        // dp[i][j] = true if s[i..j] is a palindrome
        val dp = Array(s.length) { BooleanArray(s.length) }

        for(i in s.indices) {
            println("--------------- Doing index: $i ---------------")
            dp[i][i] = true // every single character itself is a palindrome
            /**
             * iterating like..
             * [i, j]
             * [1, 0]
             * [2, 0], [2, 1]
             * [3, 0], [3, 1], [3, 2]
             * ...
             */
            for (j in 0..<i) {
                println("i: $i, j: $j")
                // for every two characters, if they are the same, it is a palindrome
                if (s[i] == s[j]) {
                    if (i - j <= 2) {
                        // if the length of the substring is 2 or less, it is a palindrome
                        dp[j][i] = true
                    } else {
                        // if the length of the substring is more than 2, check if the substring between them is a palindrome
                        dp[j][i] = dp[j + 1][i - 1]
                    }

                    // if the substring is a palindrome and its length is greater than maxLen, update maxLen and start
                    if (dp[j][i] && i - j + 1 > maxLen) {
                        maxLen = i - j + 1
                        start = j
                    }
                }

                println("[")
                dp.forEach { println("[${it.joinToString(", ")}]") }
                println("]")
                println()
            }
        }

        return s.substring(start, start + maxLen)
    }

    val s1 = "babad"
    println(longestPalindrome(s1)) // bab

    val s2 = "cbbd"
    println(longestPalindrome(s2)) // bb
}
