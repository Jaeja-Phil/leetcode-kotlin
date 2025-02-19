package medium

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * Constraints:
 * - 1 <= s.length <= 1000
 * - s consists of lowercase English letters.
 *
 * Example 1:
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
fun main() {
    fun printDP(dp: Array<BooleanArray>) {
        dp.forEach { row ->
            row.forEach { print(if (it) "T " else "F ") }
            println()
        }
        println("----------------")
    }

    fun countSubstrings(s: String): Int {
        val n = s.length
        val dp = Array(n) { BooleanArray(n) }
        var count = 0

        for (start in dp.lastIndex downTo 0) {
            for (end in start..dp.lastIndex) {
                if (
                    s[start] == s[end] &&
                    (end - start <= 2 || dp[start + 1][end - 1])
                ) {
                    dp[start][end] = true
                    count++
                }
                // printDP(dp)
            }
        }

        return count
    }

    val tests = listOf(
        "abc", // 3
        "aaa", // 6
        "fdsklf", // 6
    )
    tests.forEach { test -> println(countSubstrings(test)) }
}