package medium

/**
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other
 * uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above
 * operations.
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of only uppercase English letters.
 * - 0 <= k <= s.length
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 */
fun main() {
    fun characterReplacement(s: String, k: Int): Int {
        val charCountMap = mutableMapOf<Char, Int>()
        var left = 0
        var maxCount = 0
        var maxFrequency = 0

        for (right in s.indices) {
            val rightCharCount = charCountMap.getOrDefault(s[right], 0) + 1
            charCountMap[s[right]] = rightCharCount
            maxFrequency = maxOf(maxFrequency, rightCharCount)

            if (right - left + 1 - maxFrequency > k) {
                charCountMap[s[left]] = charCountMap[s[left]]!! - 1
                left++
            }

            maxCount = maxOf(maxCount, right - left + 1)
        }

        return maxCount
    }

    println(characterReplacement("ABAB", 2)) // 4
    println(characterReplacement("AABABBA", 1)) // 4
}
