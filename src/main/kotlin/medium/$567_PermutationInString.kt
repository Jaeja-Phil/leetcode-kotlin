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
        // Solution 1
//        val s1CharCountMap = s1.groupingBy { it }.eachCount().toMutableMap()
//        var left = 0
//        val charCountMap = mutableMapOf<Char, Int>()
//        for (right in s2.indices) {
//            val rightChar = s2[right]
//            charCountMap[rightChar] = charCountMap.getOrDefault(rightChar, 0) + 1
//            if (right - left + 1 < s1.length) {
//                continue
//            }
//
//            if (charCountMap == s1CharCountMap) {
//                return true
//            }
//
//            charCountMap[s2[left]] = charCountMap[s2[left]]!! - 1
//            if (charCountMap[s2[left]] == 0) {
//                charCountMap.remove(s2[left])
//            }
//            left++
//        }
//
//        return false

        // Solution 2
        if (s1.length > s2.length) return false

        val s1CharCountMap = IntArray(26)
        val s2CharCountMap = IntArray(26)
        for (i in s1.indices) {
            s1CharCountMap[s1[i] - 'a']++
            s2CharCountMap[s2[i] - 'a']++
        }

        var matches = 0
        for (i in 0..<26) {
            if (s1CharCountMap[i] == s2CharCountMap[i]) {
                matches++
            }
        }

        var left = 0
        for (right in s1.length..<s2.length) {
            if (matches == 26) return true

            val index = s2[right] - 'a'
            s2CharCountMap[index]++
            if (s2CharCountMap[index] == s1CharCountMap[index]) {
                matches++
            } else if (s2CharCountMap[index] == s1CharCountMap[index] + 1) {
                matches--
            }

            val leftIndex = s2[left] - 'a'
            s2CharCountMap[leftIndex]--
            if (s2CharCountMap[leftIndex] == s1CharCountMap[leftIndex]) {
                matches++
            } else if (s2CharCountMap[leftIndex] + 1 == s1CharCountMap[leftIndex]) {
                matches--
            }
            left++
        }

        return matches == 26
    }

    println(checkInclusion("ab", "eidbaooo")) // true
    println(checkInclusion("ab", "eidboaoo")) // false
}
