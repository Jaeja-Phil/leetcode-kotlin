package medium

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * Constraints:
 * - 1 <= s1.length, s2.length <= 10^4
 * - s1 and s2 consist of lowercase English letters.
 *
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 *
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 */
fun main() {
    fun checkInclusion(s1: String, s2: String): Boolean {
        val s1CharCountMap = s1.groupingBy { it }.eachCount().toMutableMap()
        var left = 0
        val charCountMap = mutableMapOf<Char, Int>()
        for (right in s2.indices) {
            val rightChar = s2[right]
            charCountMap[rightChar] = charCountMap.getOrDefault(rightChar, 0) + 1
            if (right - left + 1 < s1.length) {
                continue
            }

            if (charCountMap == s1CharCountMap) {
                return true
            }

            charCountMap[s2[left]] = charCountMap[s2[left]]!! - 1
            if (charCountMap[s2[left]] == 0) {
                charCountMap.remove(s2[left])
            }
            left++
        }

        return false
    }

    println(checkInclusion("ab", "eidbaooo")) // true
    println(checkInclusion("ab", "eidboaoo")) // false
}
