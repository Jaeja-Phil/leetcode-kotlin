package medium

import java.util.*

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Constraints:
 * - 0 <= s.length <= 5 * 10^4
 * - s consists of English letters, digits, symbols and spaces.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 */
fun main() {
    fun lengthOfLongestSubstring(s: String): Int {
        val seen = mutableSetOf<Char>()
        var windowStart = 0
        var maxLen = 0
        for (windowEnd in s.indices) {
            val currentChar = s[windowEnd]
            while (seen.add(currentChar).not()) {
                seen.remove(s[windowStart++])
            }
            maxLen = maxOf(maxLen, windowEnd - windowStart + 1)
        }
        return maxLen
    }

    println(lengthOfLongestSubstring("abcabcbb")) // 3
    println(lengthOfLongestSubstring("bbbbb")) // 1
    println(lengthOfLongestSubstring("pwwkew")) // 3
    println(lengthOfLongestSubstring(" ")) // 1
}