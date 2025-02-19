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
            // for every single character, it is a palindrome
            dp[i][i] = true
            // for every two characters, if they are the same, it is a palindrome
            // but, need to check if n + 1 is in bounds
            if (i < n - 1 && s[i] == s[i + 1]) {
                dp[i][i + 1] = true
                start = i
                maxLength = 2
            }
        }

        // now, with every single character and two characters(which are adjacent to the single character) are checked
        // for palindrome, we can check for length 3 and above
        // how checking works?
        // - if start and end characters are same and the substring between them is a palindrome, then the whole
        //   substring is a palindrome
        for (len in 3..n) {
            // ex: n = 5, len = 3 --> [0, 1, 2], [1, 2, 3], [2, 3, 4]
            for (substrStart in 0..n - len) {
                val substrEnd = substrStart + len - 1
                if (s[substrStart] == s[substrEnd] && dp[substrStart + 1][substrEnd - 1]) {
                    dp[substrStart][substrEnd] = true
                    start = substrStart
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
